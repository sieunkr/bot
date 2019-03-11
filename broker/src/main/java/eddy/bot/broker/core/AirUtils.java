package eddy.bot.broker.core;

public class AirUtils {

    public static String getTextGrade(String grade){
        String result;
        
        switch(grade){
            case "1" :
                result = "좋음 :heart_eyes: ";
                break;
            case "2" :
                result = "보통 :confused: ";
                break;
            case "3" :
                result = "나쁨 :mask: ";
                break;
            case "4" :
                result = "아주나쁨 :rage: ";
                break;
            default:
                result = "데이터 없음";
        }
        return result;
    }

}
