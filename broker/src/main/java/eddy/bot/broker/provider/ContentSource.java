package eddy.bot.broker.provider;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Eddy Kim
 *
 */
public interface ContentSource {

    @Output("BOT-SOURCE-CONTENT")
    MessageChannel output();
}
