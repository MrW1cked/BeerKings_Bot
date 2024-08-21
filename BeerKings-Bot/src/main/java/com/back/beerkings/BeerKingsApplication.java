package com.back.beerkings;

import com.back.beerkings.adapters.BeerKingsBotUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@SpringBootApplication
@Slf4j
public class BeerKingsApplication {

    public static void main(String[] args) {

        // Initialize Api Context
        ApplicationContext context = SpringApplication.run(BeerKingsApplication.class, args);
        BeerKingsBotUseCase botUseCase = context.getBean(BeerKingsBotUseCase.class);

        try {
            // Get the bot token from the environment variables
            String botToken = System.getenv("BOT_TOKEN");
            // Start the bot
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
                // Register the bot with the bot token and the use case
                botsApplication.registerBot(botToken, botUseCase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}