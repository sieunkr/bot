package eddy.bot.broker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface AirDetails {

    void fetch();
    void update(Air air);
    Flux<AirDto> findAll();
    Mono<AirDto> findByStationName(String stationName);
}
