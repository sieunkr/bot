package eddy.bot.telegram;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.telegram.telegrambots.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class TelegramApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TelegramApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();

        api.registerBot(new TelegramLongPollingBot() {

            @Override
            public void onUpdateReceived(Update update) {
                if (update.hasMessage() && update.getMessage().hasText()) {
                    SendMessage message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(update.getMessage().getText());

                    try {
                        execute(message); // Call method to send the message
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public String getBotUsername() {
                return "eddykim_bot";
            }

            @Override
            public String getBotToken() {
                return "발급받은토큰";
            }

        });
    }
}
