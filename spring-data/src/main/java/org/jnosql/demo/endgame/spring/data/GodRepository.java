package org.jnosql.demo.endgame.spring.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GodRepository extends MongoRepository<God, String> {

    List<God> findByNameContaining(String name);
}
