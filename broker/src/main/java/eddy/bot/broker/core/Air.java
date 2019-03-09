package eddy.bot.broker.core;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "response")
public class Air {

    @Getter
    @Setter
    private Header header;

    @Getter
    @Setter
    private Body body;


    @XmlRootElement(name="header")
    @Getter
    @Setter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @XmlRootElement(name="body")
    public static class Body {

        private List<Item> items;

        //TODO:lombok 적용시 @XmlElementWrapper 를 어떻게 해야할지 모르겠음
        @XmlElementWrapper(name="items")
        @XmlElement(name="item")
        public List<Item> getItems(){
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }


        @XmlRootElement(name="item")
        @Data
        public static class Item{

            private String stationName;
            private String mangName;
            private String dataTime;
            private String so2Value;
            private String coValue;
            private String o3Value;
            private String no2Value;
            private String pm10Value;
            private String pm10Value24;
            private String pm25Value;
            private String pm25Value24;
            private String khaiValue;
            private String khaiGrade;
            private String so2Grade;
            private String coGrade;
            private String o3Grade;
            private String no2Grade;
            private String pm10Grade;
            private String pm25Grade;
            private String pm10Grade1h;
            private String pm25Grade1h;

        }
    }





}
