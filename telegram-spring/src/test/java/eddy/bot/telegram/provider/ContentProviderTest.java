package eddy.bot.telegram.provider;

import eddy.bot.telegram.core.Content;
import eddy.bot.telegram.core.ContentRepository;
import eddy.bot.telegram.core.KeywordRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("컨텐츠 제공 컴포넌트")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContentProviderTest {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @BeforeAll
    static void initializeBeforeAll() {


    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("MongoDB 에서 컨텐츠 조회")
    void findByNameTest() {

        contentRepository.deleteByName("테스트 미세먼지").subscribe();

        List<String> keywords = Arrays.asList("테스트" + " " + "미세먼지");

        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("stationName", "테스트");
        data.put("dateTime", "2019-03-16 18:00");
        data.put("so2Value", "0.004");
        data.put("coValue", "0.5");
        data.put("o3Value", "0.026");
        data.put("no2Value", "0.038");
        data.put("pm10Value", "38");
        data.put("pm10Value24", "46");
        data.put("pm25Value", "23");
        data.put("pm25Value24", "27");
        data.put("khaiValue", "79");

        data.put("khaiGrade", getTextGrade("2"));
        data.put("so2Grade", getTextGrade("1"));
        data.put("coGrade", getTextGrade("1"));
        data.put("o3Grade", getTextGrade("1"));
        data.put("no2Grade", getTextGrade("2"));
        data.put("pm10Grade", getTextGrade("2"));
        data.put("pm25Grade", getTextGrade("2"));
        data.put("pm10Grade1h", getTextGrade("2"));
        data.put("pm25Grade1h", getTextGrade("2"));

        contentRepository.save(new Content("테스트" + " " + "미세먼지","weather-air-details", data, keywords,""))
                .subscribe();

        contentRepository.findByName("테스트 미세먼지")
                .subscribe(result -> {
                    assertEquals("테스트 미세먼지", result.getName());
                    assertEquals("보통 :confused: ", result.getData().get("pm10Grade"));
                    assertEquals("38", result.getData().get("pm10Value"));
                });

    }


    @Test
    @DisplayName("Trie 자료구조에서 검색 키워드 조회")
    void searchByKeywordTest() {

        keywordRepository.getKeywords().put("테스트 미세먼지", "테스트 미세먼지");
        keywordRepository.getKeywords().put("테스트 미세먼지를 알고싶다", "테스트 미세먼지");
        keywordRepository.getKeywords().put("테스트 미세먼지 정보", "테스트 미세먼지");

        assertAll("keyword",
                () -> assertEquals("테스트 미세먼지",
                        keywordRepository.getKeywords().getValueForExactKey("테스트 미세먼지 정보")),
                () -> assertEquals("테스트 미세먼지",
                        keywordRepository.getKeywords().getValueForExactKey("테스트 미세먼지를 알고싶다"))
        );
    }


    @Test
    @DisplayName("메시지로 전달 받은 컨텐츠의 키워드를 동기화")
    void syncOneTest() {



    }



    @Test
    @DisplayName("컨텐츠 템플릿팅")
    void templateTest() {

    }


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