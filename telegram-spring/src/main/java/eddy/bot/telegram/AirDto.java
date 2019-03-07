package eddy.bot.telegram;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//TODO:Data 막 사용하면 안됨.

@Setter
@Getter
public class AirDto {

    private String stationName;
    private String pm10Grade;

    AirDto(){

    }

    AirDto(String stationName, String pm10Grade){
        this.stationName = stationName;
        this.pm10Grade = pm10Grade;
    }


    public String getPm10Grade() {

        //TODO:Enum 으로
        if(pm10Grade.equals("1")){
            return "좋음";
        }
        else if(pm10Grade.equals("2")){
            return "보통";
        }
        else if(pm10Grade.equals("3")){
            return "나쁨";
        }
        else if(pm10Grade.equals("4")){
            return "매우나쁨";
        }
        else{
            return "데이터 없음";
        }
    }
}
