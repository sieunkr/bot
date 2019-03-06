package eddy.bot.broker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController {

    private final AirDetails airDetails;

    public HomeController(AirDetails airDetails) {
        this.airDetails = airDetails;
    }

    @GetMapping
    public void test(){

        airDetails.fetch();

    }
}
