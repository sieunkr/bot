package eddy.bot.telegram.provider;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import eddy.bot.telegram.core.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class ContentProvider implements ContentDetails {

    private final ContentRepository contentRepository;
    private final KeywordRepository keywordRepository;
    private final CategoryRepository categoryRepository;

    public ContentProvider(ContentRepository contentRepository, KeywordRepository keywordRepository, CategoryRepository categoryRepository) {
        this.contentRepository = contentRepository;
        this.keywordRepository = keywordRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Mono<Content> findByName(String name) {

        return contentRepository.findByName(name);
    }


    @Override
    public Mono<Content> searchByKeyword(String keyword) {
    
        //TODO: 중복 코드
        if(keywordRepository.getKeywords().getValueForExactKey(keyword.replaceAll(" ", "")) != null){
            return findByName(keywordRepository.getKeywords().getValueForExactKey(keyword.replaceAll(" ", "")));
        }
        else{
            return Mono.empty();
        }
    }


    @Override
    public Mono<Void> syncOne(String name) {

        findByName(name).subscribe(content -> {
                    content.getKeywords().forEach(keyword->{
                        keywordRepository.getKeywords().put(keyword.replaceAll(" ", ""), content.getName());
                    });
                },
                null,
                null);

        return Mono.empty();

    }


    @Override
    public Mono<String> template(Content content) {
        return Mono.create(monoSink -> {
                    categoryRepository.findByCategoryId(content.getCategoryId()).subscribe(category -> {
                        String template = category.getTemplate();
                        Template tmpl = Mustache.compiler().compile(template);
                        monoSink.success(tmpl.execute(content.getData()));
                    });
                }
        );
    }
}
