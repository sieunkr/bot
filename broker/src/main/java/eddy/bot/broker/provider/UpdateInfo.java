package eddy.bot.broker.provider;

import java.io.Serializable;

public class UpdateInfo implements Serializable {
    private static final long serialVersionUID = 2L;

    private String name;

    public UpdateInfo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
