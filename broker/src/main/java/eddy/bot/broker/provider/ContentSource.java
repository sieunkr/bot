package eddy.bot.broker.provider;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

public interface ContentSource {

    @Output("BOT-SOURCE-CONTENT")
    MessageChannel output();
}
