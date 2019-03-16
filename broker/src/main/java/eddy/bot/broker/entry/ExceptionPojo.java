package eddy.bot.broker.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Eddy Kim
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionPojo {

    @JsonProperty(value = "status_code")
    private String status_code;

    @JsonProperty(value = "message")
    private String message;

    public ExceptionPojo(String status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }
}
