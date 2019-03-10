package eddy.bot.telegram.entry;

import com.vdurmont.emoji.EmojiParser;
import eddy.bot.telegram.core.Content;
import eddy.bot.telegram.provider.ContentProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Component
public class TelegramMessageListener {

    @Value("${broker.airs.url}")
    private String brokerAirsUrl;

    @Value("${telegram.bot.name}")
    private String telegramBotName;

    @Value("${telegram.bot.key}")
    private String telegramBotKey;

    private final ContentProvider contentProvider;

    public TelegramMessageListener(ContentProvider contentProvider){
        this.contentProvider = contentProvider;
    }


    @PostConstruct
    private void init() {

        //TODO:Bean 으로...
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        
        try {
            api.registerBot(new TelegramLongPollingBot() {

                @Override
                public void onUpdateReceived(Update update) {
                    if (update.hasMessage() && update.getMessage().hasText()) {

                        //TODO:컴포넌트 및 메서드 분리
                        String stringMessage = update.getMessage().getText();
                        SendMessage message = new SendMessage()
                                .enableHtml(true);

                        contentProvider.searchByKeyword(stringMessage)
                                .switchIfEmpty(
                                        Mono.create(monoSink -> {
                                                    Content content = new Content("not-found","not-found", null, null,"");
                                                    monoSink.success(content);
                                                }
                                        )
                                )
                                .subscribe(content -> {
                                    contentProvider.template(content).subscribe(s -> {
                                        message.setChatId(update.getMessage().getChatId())
                                                .setText(EmojiParser.parseToUnicode(s));

                                        try {
                                            execute(message);
                                        } catch (TelegramApiException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                });
                    }
                }

                @Override
                public String getBotUsername() {
                    return telegramBotName;
                }

                @Override
                public String getBotToken() {
                    return telegramBotKey;
                }

            });
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
