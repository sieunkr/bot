package eddy.bot.broker.core;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AirUseCase {

    private final AirDetails airDetails;

    public AirUseCase(AirDetails airDetails) {
        this.airDetails = airDetails;
    }

    public Flux<Content> findAll(){
        return airDetails.findAll();
    }

    public Mono<Content> findByStationName(String stationName){
        return airDetails.findByStationName(stationName);
    }

}
