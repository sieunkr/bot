package eddy.bot.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@SpringBootApplication
public class TelegramApplication implements CommandLineRunner {

    @Value("${broker.airs.url}")
    private String brokerAirsUrl;

    private final RestTemplate restTemplate;

    public TelegramApplication(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

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


                    //TODO:컴포넌트 및 메서드 분리
                    String stringMessage = update.getMessage().getText();
                    SendMessage message = new SendMessage();

                    //TODO:
                    if(stringMessage.equals("미세먼지")){

                        try {

                            ResponseEntity<List<AirDto>> rateResponse =
                                    restTemplate.exchange(brokerAirsUrl,
                                            HttpMethod.GET, null, new ParameterizedTypeReference<List<AirDto>>() {
                                            });
                            List<AirDto> airs = rateResponse.getBody();

                            StringBuilder stringBuilder = new StringBuilder();
                            for (AirDto air : airs) {
                                stringBuilder.append(air.getStationName())
                                                .append(" ")
                                                .append("미세먼지:")
                                                .append(air.getPm10Grade())
                                                .append("\r\n");
                            }

                            message.setChatId(update.getMessage().getChatId())
                                    .setText(stringBuilder.toString());
                            execute(message);

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                        /*
                        //TODO:Reactive Programming
                        WebClient webClient = WebClient.create();
                        webClient.get().uri(brokerAirsUrl)
                                .retrieve()
                                .bodyToFlux(AirDto.class)
                                .collectList()
                                .subscribe(
                                    //consumer
                                    air -> {
                                        message.setChatId(update.getMessage().getChatId())
                                                .setText("미세먼지 정보를 전달합니다.");
                                        try {
                                            execute(message); // Call method to send the message
                                        } catch (TelegramApiException e) {
                                            e.printStackTrace();
                                        }
                                    },
                                    //error
                                    null,
                                    //complete
                                    () -> {

                                    });
                        */


                    }
                    else{
                        message.setChatId(update.getMessage().getChatId())
                                .setText("네?");

                        try {
                            execute(message); // Call method to send the message
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }



                }
            }

            @Override
            public String getBotUsername() {
                return "eddykim_bot";
            }

            @Override
            public String getBotToken() {
                return "비밀";
            }

        });
    }
}
