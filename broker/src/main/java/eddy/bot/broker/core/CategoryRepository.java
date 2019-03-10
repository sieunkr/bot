package eddy.bot.broker.core;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveMongoRepository<Category, Integer> {
    Mono<Category> findByCategoryId(String categoryId);
}