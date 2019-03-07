package eddy.bot.broker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AirProvider implements AirDetails {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    private ConcurrentHashMap<String,AirDto> airHashMap = new ConcurrentHashMap<>();

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
                item -> airHashMap.put(item.getStationName(), new AirDto(item.getStationName(),item.getPm10Grade())));
    }

    @Override
    public Flux<AirDto> findAll() {
        return Flux.fromIterable(airHashMap.values());
    }

    @Override
    public Mono<AirDto> findByStationName(String stationName) {
        return Mono.just(airHashMap.get(stationName));
    }


}
