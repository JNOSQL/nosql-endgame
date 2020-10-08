package org.jnosql.demo.endgame.spring.boot.data.mongodb.repository;

import org.jnosql.demo.endgame.spring.boot.data.mongodb.model.God;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GodRepository extends MongoRepository<God, String> {

    List<God> findByNameContaining(String name);
}
