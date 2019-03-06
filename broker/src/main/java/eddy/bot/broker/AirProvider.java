package eddy.bot.broker;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class AirProvider implements AirDetails {




    @Override
    public void fetch() {




        /*
        MultiValueMap params = new LinkedMultiValueMap<>();
        params.put("serviceKey","fvfQ6YjRGHPX9zvbZNtlNjdFaFkG%2F%2FHT%2BZ%2BCUQH18dVOOJTOIqD4duXdTd4QJ3pY31VKL%2FXOUcDpG5S%2FSxqBaQ%3D%3D");
        params.put("numOfRows","10");
        params.put("pageNo","1");
        params.put("sidoName","서울");
        params.put("searchCondition","HOUR");
        */

        try {
            URI requestUri = new URI("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?serviceKey=&numOfRows=10&pageNo=1&sidoName=%EC%84%9C%EC%9A%B8&searchCondition=HOUR");

            WebClient webClient = WebClient.create();

            webClient.get().uri(requestUri)
                    .retrieve()
                    .bodyToMono(Air.class)
                    .subscribe(a -> {
                        System.out.println("테스트");
                    });


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        /*
        URI uri = UriComponentsBuilder.fromHttpUrl("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst")
                .queryParam("serviceKey","fvfQ6YjRGHPX9zvbZNtlNjdFaFkG%2F%2FHT%2BZ%2BCUQH18dVOOJTOIqD4duXdTd4QJ3pY31VKL%2FXOUcDpG5S%2FSxqBaQ%3D%3D")
                .queryParam("numOfRows","10")
                .queryParam("pageNo","1")
                .queryParam("sidoName","서울")
                .queryParam("searchCondition","HOUR")
                .build().toUri();
        */






        /*
        this.webClient.get().uri(uri.toUri())
                .retrieve()
                .bodyToMono(Air.class)
                .subscribe(a -> {
                    System.out.println("테스트");
                });
        */

    }
}
