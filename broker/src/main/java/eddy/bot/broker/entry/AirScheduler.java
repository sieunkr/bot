package eddy.bot.broker.entry;

import eddy.bot.broker.provider.AirProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Eddy Kim
 *
 */
@Component
public class AirScheduler {

    private final AirProvider airProvider;

    public AirScheduler(AirProvider airProvider) {
        this.airProvider = airProvider;
    }

    @Scheduled(fixedDelay = 600000)
    public void cronJob() {
        airProvider.fetch();
    }

}
