package eddy.bot.broker.core;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author Eddy Kim
 *
 */
public interface ContentRepository extends ReactiveMongoRepository<Content, Integer> {
    Mono<Content> findByName(String name);
}