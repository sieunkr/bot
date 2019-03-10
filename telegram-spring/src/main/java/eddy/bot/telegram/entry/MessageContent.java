package eddy.bot.telegram.entry;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageContent implements Serializable {

    private String name;
}
