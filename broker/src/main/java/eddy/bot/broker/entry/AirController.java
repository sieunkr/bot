package eddy.bot.broker.entry;

import eddy.bot.broker.core.AirDetails;
import eddy.bot.broker.core.Content;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/airs")
public class AirController {

    //TODO: AirUseCase 로 분리
    private final AirDetails airDetails;

    public AirController(AirDetails airDetails) {
        this.airDetails = airDetails;
    }

    @GetMapping
    public Flux<Content> findAll(){
        return airDetails.findAll();
    }

    @GetMapping("{stationName}")
    public Mono<Content> findByStationName(@PathVariable(name = "stationName") String stationName){
        return airDetails.findByStationName(stationName);
    }

}
