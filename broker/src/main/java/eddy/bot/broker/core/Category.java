package eddy.bot.broker.core;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Data
public class Category {

    @Id
    private String id;

    @Indexed
    private String categoryId;

    private String name;

    private String template;
}