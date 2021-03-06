package eddy.bot.broker.core;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Eddy Kim
 *
 */
public interface AirDetails {

    void fetch();
    void update(Air air);

    Flux<Content> findAll();
    Mono<Content> findByStationName(String stationName);
}
