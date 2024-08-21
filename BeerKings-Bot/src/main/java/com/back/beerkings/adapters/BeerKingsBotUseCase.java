package com.back.beerkings.adapters;

import com.back.beerkings.helpers.Messages;
import com.back.beerkings.models.database.LanguageMO;
import com.back.beerkings.models.database.PodiumMO;
import com.back.beerkings.models.database.PodiumPK;
import com.back.beerkings.repositories.LanguageRepository;
import com.back.beerkings.repositories.PodiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeerKingsBotUseCase implements LongPollingSingleThreadUpdateConsumer {

    private String locale;
    private final PodiumRepository repository;
    private final LanguageRepository languageRepository;
    private final Messages messages = new Messages();


    @Override
    public void consume(Update incommingMessage) {

        String messageText = incommingMessage.getMessage().getText(); //will store the User message
        String messageBody = ""; //will store the output message
        Messages messages = new Messages();

        // We check if the incommingMessage has a message and the message has text
        if (incommingMessage.hasMessage() && incommingMessage.getMessage().hasText()) {
            locale = checkGroupLanguage(incommingMessage);
            messageBody = switch (messageText) {
                case "/drink", "/drink@BeerKings_bot" -> {
                    updateUserScorePlusOne(incommingMessage);
                    yield messages.getRandomMessage(locale);
                }
                case "/top", "/top@BeerKings_bot" -> getTopUsers(incommingMessage, locale);
                case "/info", "/info@BeerKings_bot" -> getWelcomeMessage(locale);
                case "/language_en", "/language_en@BeerKings_bot" -> setUpGoupLanguage(incommingMessage, "en");
                case "/language_pt", "/language_pt@BeerKings_bot" -> setUpGoupLanguage(incommingMessage, "pt");
                case "/language_es", "/language_es@BeerKings_bot" -> setUpGoupLanguage(incommingMessage, "es");
                default -> "";
            };
            sendMessage(messageBody, incommingMessage);
        }
    }


    private String checkGroupLanguage(Update incommingMessage) {
        languageRepository.findById(incommingMessage.getMessage().getChatId()).ifPresentOrElse(
                language -> locale = language.getLanguage(),
                () -> locale = setUpGoupLanguage(incommingMessage, "en")
        );
        return locale;
    }

    private String setUpGoupLanguage(Update incommingMessage, String locale) {
        LanguageMO language = LanguageMO.builder()
                .chatId(incommingMessage.getMessage().getChatId())
                .groupName(incommingMessage.getMessage().getChat().getTitle())
                .language(locale)
                .build();
        languageRepository.save(language);
        return locale + "✅";

    }

    private void sendMessage(String messageBody, Update incommingMessage) {
        TelegramClient telegramClient = new OkHttpTelegramClient(System.getenv("BOT_TOKEN"));
        log.info("BOT_TOKEN: " + System.getenv("BOT_TOKEN"));
        SendMessage message = prepareMessage(messageBody, incommingMessage);
        if (!messageBody.isEmpty()) {
            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private static SendMessage prepareMessage(String messageBody, Update incommingMessage) {
        return SendMessage.builder()
                .text(messageBody)
                .parseMode("MarkdownV2")// Use Markdown so that we can use bolds and Italics if needed
                .chatId(incommingMessage.getMessage().getChatId())
                .replyToMessageId(incommingMessage.getMessage().getMessageId())
                .build();
    }

    private String getWelcomeMessage(String locale) {
        return messages.getInfoMessage(locale);
    }

    private String getTopUsers(Update incommingMessage, String locale) {
        Map<String, Integer> podium = getPodium(incommingMessage).stream().collect(
                Collectors.toMap(PodiumMO::getName, PodiumMO::getDrinks));
        //the map above has a name and its score (drinks). now we need to sort it by score and add to the message the User name, and its score (drinks)
        return messages.getTopMessage(locale) +
                podium.entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .map(entry -> entry.getKey() +
                                ": " +
                                entry.getValue() + " \uD83C\uDF7A")
                        .collect(Collectors.joining("\n"));
    }

    public void updateUserScore(PodiumMO podiumMO) {
        repository.save(podiumMO);
    }

    public void updateUserScorePlusOne(Update incommingMessage) {
        PodiumMO podiumMO = createUserIfDontExists(incommingMessage);
        podiumMO.setDrinks(podiumMO.getDrinks() + 1);
        log.info(podiumMO.getName() + ": " + podiumMO.getDrinks());
        updateUserScore(podiumMO);
    }

    public PodiumMO createUserIfDontExists(Update update) {
        try {
            return getUser(update);
        } catch (Exception e) {
            PodiumMO podiumMO = PodiumMO.builder()
                    .id(update.getMessage().getFrom().getId())
                    .chatId(update.getMessage().getChatId())
                    .name(update.getMessage().getFrom().getFirstName())
                    .drinks(0)
                    .build();
            updateUserScore(podiumMO);
            return podiumMO;
        }
    }

    public PodiumMO getUser(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        Long chatId = update.getMessage().getChatId();

        PodiumPK pk = PodiumPK.builder()
                .id(userId)
                .chatId(chatId)
                .build();
        return repository.findById(pk).orElseThrow();
    }

    public List<PodiumMO> getPodium(Update update) {
        return repository.findAllByChatId(update.getMessage().getChatId());
    }

    @Override
    public void consume(List<Update> updates) {
        LongPollingSingleThreadUpdateConsumer.super.consume(updates);
    }

}
