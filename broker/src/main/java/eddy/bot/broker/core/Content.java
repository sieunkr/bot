package eddy.bot.broker.core;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.List;

@Document(collection = "contents")
@Data
public class Content {

    @Id
    private String id;
    //TODO:같은 name의 데이터(동명이인,동음이의어 등)를 처리할 수 없음
    //추후에 Auto increment ID 로 개선하는게 좋지 않을까?
    @Indexed
    private String name;
    private LinkedHashMap<String,Object> data;
    private List<String> keywords;
    private String description;

    public Content(String name, LinkedHashMap<String,Object> data, List<String> keywords, String description){
        this.name = name;
        this.data = data;
        this.keywords = keywords;
        this.description = description;
    }

}