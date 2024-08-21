package com.back.beerkings.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Messages {

    private List<String> sentencesPT;
    private List<String> sentencesEN;
    private List<String> sentencesES;
    private String drink;
    private String info;
    private String top;
    private String localeMessage;

    public Messages() {
        //Portuguese sentences
        sentencesPT = new ArrayList<>();
        sentencesEN = new ArrayList<>();
        sentencesES = new ArrayList<>();
        sentencesPT.add("Tantas cervejas, estás a criar um rio de lúpulo\\!");
        sentencesPT.add("Com essa quantidade, estás a patrocinar a fábrica\\!");
        sentencesPT.add("Um brinde ao fígado, que faz horas extras\\!");
        sentencesPT.add("Se beber fosse profissão, estavas milionário/a\\!");
        sentencesPT.add("Mais uma dessas e precisas de um GPS para encontrar o caminho de volta\\!");
        sentencesPT.add("O copo está cheio, e a coragem também\\!");
        sentencesPT.add("Estás a hidratar-te… mas só por dentro\\!");
        sentencesPT.add("Cada gole é uma nova aventura\\!");
        sentencesPT.add("Se continuar assim, a cerveja vai-te pedir em casamento\\!");
        sentencesPT.add("Olha só, mais uma e ganhas o prémio de bebedor do mês\\!");
        sentencesPT.add("Estás a ficar mais leve… ou é só a gravidade a puxar-te para o chão\\?");
        sentencesPT.add("A tua cerveja tem mais espuma que o teu champô\\!");
        sentencesPT.add("Com tanto brindes, até o copo já perdeu a conta\\!");
        sentencesPT.add("Cuidado, daqui a pouco vais começar a ver tudo em duplicado\\!");
        sentencesPT.add("Depois desta, nem o Super-Homem te acompanha\\!");
        sentencesPT.add("Estás a criar memórias… que vais esquecer amanhã\\!");
        sentencesPT.add("Com esse ritmo, amanhã vai ser uma aventura\\!");
        sentencesPT.add("Mais uma e vais ter que fazer amigos novos para contar as histórias\\!");
        sentencesPT.add("Estás a dar vida ao ditado: \\'Água mole em pedra dura tanto bate até que fura\\.\\'");
        sentencesPT.add("Um copo na mão e outro na cabeça\\!");

        // English sentences
        sentencesEN.add("You\\'re creating a river of hops with all that beer\\!");
        sentencesEN.add("With that amount, you\\'re sponsoring the brewery\\!");
        sentencesEN.add("A toast to your liver, working overtime\\!");
        sentencesEN.add("If drinking beer was a job, you\\'d be a billionaire\\!");
        sentencesEN.add("Another one of those, and you\\'ll need a GPS to find your way back\\!");
        sentencesEN.add("The glass is full, and so is your courage\\!");
        sentencesEN.add("You\\'re staying hydrated… on the inside\\!");
        sentencesEN.add("Every sip is a new adventure\\!");
        sentencesEN.add("At this rate, the beer will propose to you\\!");
        sentencesEN.add("Look at that, one more and you\\'ll win the drinker of the month award\\!");
        sentencesEN.add("You\\'re getting lighter… or is gravity just pulling you down\\?");
        sentencesEN.add("Your beer has more foam than your shampoo\\!");
        sentencesEN.add("With so many cheers, even the glass has lost count\\!");
        sentencesEN.add("Careful, soon you\\'ll start seeing double\\!");
        sentencesEN.add("After this one, even Superman can\\'t keep up with you\\!");
        sentencesEN.add("You\\'re making memories… that you\\'ll forget tomorrow\\!");
        sentencesEN.add("At this pace, tomorrow\\'s going to be an adventure\\!");
        sentencesEN.add("One more, and you\\'ll need new friends to tell these stories to\\!");
        sentencesEN.add("You\\'re giving life to the saying: \\'Slow and steady wins the race\\.\\'");
        sentencesEN.add("One beer in hand, and another in your head\\!");

        sentencesES.add("¡Estás creando un río de lúpulo con tanta cerveza\\!");
        sentencesES.add("¡Con esa cantidad, estás patrocinando la fábrica\\!");
        sentencesES.add("¡Un brindis por tu hígado, que está trabajando horas extras\\!");
        sentencesES.add("¡Si beber cerveza fuera un trabajo, serías multimillonario/a\\!");
        sentencesES.add("¡Otra de esas y necesitarás un GPS para encontrar el camino de vuelta\\!");
        sentencesES.add("¡El vaso está lleno, y también tu coraje\\!");
        sentencesES.add("¡Te estás hidratando… pero solo por dentro\\!");
        sentencesES.add("¡Cada sorbo es una nueva aventura\\!");
        sentencesES.add("¡A este ritmo, la cerveza te pedirá matrimonio\\!");
        sentencesES.add("¡Mira eso, una más y ganarás el premio al bebedor del mes\\!");
        sentencesES.add("¿Te estás volviendo más ligero… o es solo la gravedad que te está tirando\\?");
        sentencesES.add("¡Tu cerveza tiene más espuma que tu champú\\!");
        sentencesES.add("¡Con tantos brindis, hasta el vaso perdió la cuenta\\!");
        sentencesES.add("¡Cuidado, pronto comenzarás a ver todo doble\\!");
        sentencesES.add("¡Después de esta, ni Superman podrá seguir tu ritmo\\!");
        sentencesES.add("¡Estás creando recuerdos… que olvidarás mañana\\!");
        sentencesES.add("¡A este ritmo, mañana será una aventura\\!");
        sentencesES.add("¡Una más y necesitarás nuevos amigos para contar estas historias\\!");
        sentencesES.add("¡Estás dando vida al dicho: \\'Lento y seguro, se gana la carrera\\.\\'");
        sentencesES.add("¡Una cerveza en la mano, y otra en la cabeza\\!");
    }

    public String getLocaleMessage() {
        return """
                PT \\- Português\s
                EN \\- English\s
                ES \\- Español""";
    }

    public String getDrinkMessage(String locale) {
        return getRandomMessage(locale);
    }

    public String getInfoMessage(String locale) {
        String infoPT = "Olá\\! Eu sou o BeerKingsBot\\. O bot que conta todas as cervejas que voçês bebem neste grupo \\(podes estar em vários grupos ao mesmo tempo\\) \n" +
                "Basta criares um grupo com os teus amigos e adicionar como participante o Bot @BeerKings \\(procurem nos contactos que ele existe\\)\n \n" +
                "A lista de comandos é simples\\. Só tens de escrever uma mensagem normal com o texto\\:\n" +
                "/drink ou /drink@BeerKings\\_bot \\- Adiciona \\+1 \uD83C\uDF7A ao teu total de cervejas \n" +
                "/top ou /top@BeerKings\\_bot \\- Devolve a classificação dos bebedores deste grupo \n" +
                "/language_pt/es/en ou /language_pt/es/en@BeerKings\\_bot \\- Modifica o idioma por defeito do Bot \n" +
                "\n" +
                "Que comece a festa da \uD83C\uDF7A\\!";

        String infoEN = "Hello\\! I\\'m the **BeerKingsBot** \\. The bot that counts all the beers you guys drink in this group \\( *you can be in several groups at the same time* \\) \n" +
                "Just create a group with your friends and add the Bot @BeerKings as a participant \\( *look for it in your contacts* \\)\n \n" +
                "The list of commands is simple\\. You just have to write a normal message with the text\\:\n" +
                "/drink or /drink@BeerKings\\_bot \\- Adds \\+1 \uD83C\uDF7A to your total of beers \n" +
                "/top or /top@BeerKings\\_bot \\- Returns the ranking of the drinkers in this group \n" +
                "/language_pt/es/en or /language_pt/es/en@BeerKings\\_bot \\- Change the Bot\\'s language \n" +
                "\n" +
                "Let the \uD83C\uDF7A party begin\\!";

        String infoES = "¡Hola\\! Soy el BeerKingsBot\\. El bot que cuenta todas las cervezas que ustedes beben en este grupo \\(pueden estar en varios grupos al mismo tiempo\\) \n" +
                "Solo tienen que crear un grupo con sus amigos y agregar al Bot @BeerKings como participante \\( *búscalo en tus contactos* \\)\n \n" +
                "La lista de comandos es simple\\. Solo tienes que escribir un mensaje normal con el texto\\:\n" +
                "/drink o /drink@BeerKings\\_bot \\- Suma \\+1 \uD83C\uDF7A a tu total de cervezas \n" +
                "/top o /top@BeerKings\\_bot \\- Devuelve la clasificación de los bebedores en este grupo \n" +
                "/language_pt/es/en o /language_pt/es/en@BeerKings\\_bot \\- Cambia el idioma del Bot \n" +
                "\n" +
                "¡Que comience la fiesta de la \uD83C\uDF7A\\!";

        switch (locale) {
            case "pt" -> {
                return infoPT;
            }
            case "en" -> {
                return infoEN;
            }
            case "es" -> {
                return infoES;
            }
            default -> {
                return infoEN;
            }
        }
    }

    public String getTopMessage(String locale) {
        String topPT = "**Este grupo vai precisar de uma nova canalização para tanto lúpulo\\! \uD83C\uDF7A \uD83C\uDF7A \uD83C\uDF7A\n" +
                "Confiram as \uD83C\uDFC6classificações\uD83C\uDFC6 dos mestres da cerveja: \uD83D\uDC47** \n";
        String topEN = "**This group is going to need a new pipeline for all this beer\\! \uD83C\uDF7A \uD83C\uDF7A \uD83C\uDF7A\n" +
                "Check out the \uD83C\uDFC6beer masters\uD83C\uDFC6 rankings: \uD83D\uDC47** \n";
        String topES = "¡**Este grupo va a necesitar una nueva tubería para tanto lúpulo\\! \uD83C\uDF7A \uD83C\uDF7A \uD83C\uDF7A\n" +
                "Miren las \uD83C\uDFC6clasificaciones\uD83C\uDFC6 de los maestros cerveceros: \uD83D\uDC47** \n";
        switch (locale) {
            case "pt" -> {
                return topPT;
            }
            case "en" -> {
                return topEN;
            }
            case "es" -> {
                return topES;
            }
            default -> {
                return topEN;
            }
        }
    }

    public String getRandomMessage(String locale) {
        Random r = new Random();
        int low = 0;
        int high = 15;
        int result = r.nextInt(high - low) + low;
        return switch (locale) {
            case "pt" -> sentencesPT.get(result);
            case "en" -> sentencesEN.get(result);
            case "es" -> sentencesES.get(result);
            default -> sentencesEN.get(result);
        };
    }
}
