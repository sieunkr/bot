package eddy.bot.broker.core;

import lombok.Data;

/**
 * @author Eddy Kim
 *
 */
//TODO:Deprecated
@Data
public class AirDto {

    private String stationName;
    private String pm10Grade;

    public AirDto(String stationName, String pm10Grade){
        this.stationName = stationName;
        this.pm10Grade = pm10Grade;
    }

}
