package eddy.bot.broker;

import lombok.Data;

@Data
public class AirDto {

    private String stationName;
    private String pm10Grade;

    AirDto(String stationName, String pm10Grade){
        this.stationName = stationName;
        this.pm10Grade = pm10Grade;
    }

}
