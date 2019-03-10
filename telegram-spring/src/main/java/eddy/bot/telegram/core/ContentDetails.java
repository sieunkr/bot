package eddy.bot.telegram.core;

import reactor.core.publisher.Mono;

public interface ContentDetails {
    Mono<Content> findByName(String name);
    Mono<Content> searchByKeyword(String keyword);
    Mono<Void> syncOne(String name);
    Mono<?> template(Content content);
}