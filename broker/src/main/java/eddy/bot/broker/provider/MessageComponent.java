package eddy.bot.broker.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(ContentSource.class)
public class MessageComponent {

    @Autowired
    ContentSource customSource;

    public void sendMessagePojo(UpdateInfo updateInfo) {
        customSource.output().send(MessageBuilder.withPayload(updateInfo).build());
    }
}