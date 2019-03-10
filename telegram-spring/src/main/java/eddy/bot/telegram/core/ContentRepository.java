package eddy.bot.telegram.core;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ContentRepository extends ReactiveMongoRepository<Content, Integer> {
    Mono<Content> findByName(String name);
}