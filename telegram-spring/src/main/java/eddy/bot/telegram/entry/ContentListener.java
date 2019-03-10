package eddy.bot.telegram.entry;


import eddy.bot.telegram.provider.ContentProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(ContentListener.Sink.class)
@RequiredArgsConstructor
public class ContentListener {

    private final ContentProvider contentProvider;

    @StreamListener(Sink.inbound)
    public void updateContentSync(MessageContent messageContent) {

        contentProvider.syncOne(messageContent.getName());

    }

    public interface Sink {
        String inbound = "BOT-SINK-CONTENT";
        @Input(Sink.inbound)
        SubscribableChannel ContentListener();
    }
}
