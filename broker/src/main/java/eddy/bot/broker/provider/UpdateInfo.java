package eddy.bot.broker.provider;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateInfo implements Serializable {
    private static final long serialVersionUID = 2L;

    private String name;

    UpdateInfo(String name){
        this.name = name;
    }
}
