package org.jnosql.demo.endgame.spring.neo4j.domain;

import reactor.core.publisher.Mono;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;

public interface MovieRepository extends ReactiveNeo4jRepository<MovieEntity, String> {

    Mono<MovieEntity> findOneByTitle(String title);
}