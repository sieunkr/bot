package eddy.bot.broker;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "response")
public class Air {


    private Header header;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }


    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @XmlRootElement(name="header")
    public static class Header {

        private String resultCode;

        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    @XmlRootElement(name="body")
    public static class Body {

        private List<Item> items;

        @XmlElementWrapper(name="items")
        @XmlElement(name="item")
        public List<Item> getItems(){
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }


        @XmlRootElement(name="item")
        public static class Item{

            private String dataTime;

            private String cityName;

            private String so2Value;

            private String coValue;

            private String o3Value;

            private String no2Value;

            private String pm10Value;

            private String pm25Value;


            public String getDataTime() {
                return dataTime;
            }

            public String getCityName() {
                return cityName;
            }

            public String getSo2Value() {
                return so2Value;
            }

            public String getCoValue() {
                return coValue;
            }

            public String getO3Value() {
                return o3Value;
            }

            public String getNo2Value() {
                return no2Value;
            }

            public String getPm10Value() {
                return pm10Value;
            }

            public String getPm25Value() {
                return pm25Value;
            }

            public void setDataTime(String dataTime) {
                this.dataTime = dataTime;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public void setSo2Value(String so2Value) {
                this.so2Value = so2Value;
            }

            public void setCoValue(String coValue) {
                this.coValue = coValue;
            }

            public void setO3Value(String o3Value) {
                this.o3Value = o3Value;
            }

            public void setNo2Value(String no2Value) {
                this.no2Value = no2Value;
            }

            public void setPm10Value(String pm10Value) {
                this.pm10Value = pm10Value;
            }

            public void setPm25Value(String pm25Value) {
                this.pm25Value = pm25Value;
            }
        }
    }





}
