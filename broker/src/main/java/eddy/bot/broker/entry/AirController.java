package eddy.bot.broker.entry;

import eddy.bot.broker.core.AirUseCase;
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

    private final AirUseCase airUseCase;

    public AirController(AirUseCase airUseCase) {
        this.airUseCase = airUseCase;
    }
    
    @GetMapping
    public Flux<Content> findAll(){
        return airUseCase.findAll();
    }

    @GetMapping("{stationName}")
    public Mono<Content> findByStationName(@PathVariable(name = "stationName") String stationName){
        return airUseCase.findByStationName(stationName);
    }

}
