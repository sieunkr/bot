package eddy.bot.broker.provider;

import eddy.bot.broker.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class AirProvider implements AirDetails {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    private final ContentRepository contentRepository;

    private final MessageComponent messageComponent;

    public AirProvider(ContentRepository contentRepository, MessageComponent messageComponent) {
        this.contentRepository = contentRepository;
        this.messageComponent = messageComponent;
    }


    @Override
    public void fetch() {

        try {
            URI requestUri = new URI(weatherApiUrl);

            //TODO:Bean 주입으로 변경
            WebClient webClient = WebClient.create();

            webClient.get().uri(requestUri)
                    .retrieve()
                    .bodyToMono(Air.class)
                    .subscribe(this::update);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Air air) {

        air.getBody().getItems().forEach(
            item -> {

                //TODO:키워드 생성 메서드 분리, 관리툴 연동
                List<String> keywords = Arrays.asList(item.getStationName() + " " + "미세먼지");

                LinkedHashMap<String, Object> data = new LinkedHashMap<>();
                data.put("stationName", item.getStationName());
                data.put("dateTime", item.getDataTime());
                data.put("so2Value", item.getSo2Value());
                data.put("coValue", item.getCoValue());
                data.put("o3Value", item.getO3Value());
                data.put("no2Value", item.getNo2Value());
                data.put("pm10Value", item.getPm10Value());
                data.put("pm10Value24", item.getPm10Value24());
                data.put("pm25Value", item.getPm25Value());
                data.put("pm25Value24", item.getPm25Value24());
                data.put("khaiValue", item.getKhaiValue());
                data.put("khaiGrade", item.getKhaiGrade());
                data.put("so2Grade", item.getSo2Grade());
                data.put("coGrade", item.getCoGrade());
                data.put("o3Grade", item.getO3Grade());
                data.put("no2Grade", item.getNo2Grade());
                data.put("pm10Grade", item.getPm10Grade());
                data.put("pm25Grade", item.getPm25Grade());
                data.put("pm10Grade1h", item.getPm10Grade1h());
                data.put("pm25Grade1h", item.getPm25Grade1h());


                //TODO:ContentProvider 분리
                //TODO:매직넘버 처리
                contentRepository.findByName(item.getStationName() + " " + "미세먼지")
                        .map(findAir -> {
                            findAir.setKeywords(keywords);
                            findAir.setData(data);
                            return findAir;
                        })
                        .switchIfEmpty(
                            Mono.create(monoSink -> {
                                    Content content = new Content(item.getStationName() + " " + "미세먼지","weather-air-details", data, keywords,"");
                                    monoSink.success(content);
                                }
                            )
                        )
                        .subscribe(newOrUpdatedAir -> {
                                    contentRepository.save(newOrUpdatedAir).subscribe();

                                    //TODO: Telegram Bot 으로 메시지 전송
                                    messageComponent.sendMessagePojo(new UpdateInfo(newOrUpdatedAir.getName()));
                                },
                                null,
                                null
                        );


            }
        );
    }


    @Override
    public Flux<Content> findAll() {
        return contentRepository.findAll();
    }

    @Override
    public Mono<Content> findByStationName(String stationName) {
        //TODO:매직넘버
        return contentRepository.findByName(stationName + " " + "미세먼지");
    }


}
