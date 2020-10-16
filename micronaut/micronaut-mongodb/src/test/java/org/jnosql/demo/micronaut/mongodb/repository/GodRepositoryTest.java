package org.jnosql.demo.micronaut.mongodb.repository;

import io.micronaut.test.annotation.MicronautTest;
import java.util.List;
import javax.inject.Inject;

import org.jnosql.demo.micronaut.mongodb.model.God;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
class GodRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(GodRepositoryTest.class);

    @Inject
    GodRepository godRepository;

    @SuppressWarnings("unused")
	@Test
    @Order(1)
    void insertThenRead() {

        God ullr = godRepository.insert(new God("Ullr"));
        God thor = godRepository.insert(new God("Thor"));
        List<God> warriors = godRepository.findByName("Thor");
        List<God> hunters = godRepository.findByName("Ullr");
        log.info("{} hunters and {} warriors in the DB", hunters.size(), warriors.size());
        Assertions.assertFalse(hunters.isEmpty());
        Assertions.assertFalse(warriors.isEmpty());
    }

    @Test
    @Order(2)
    void readWarriorWithoutInsertingFirst() {
        List<God> result = godRepository.findByName("Thor");
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    @Order(3)
    void readHunterWithoutInsertingFirst() {
        List<God> result = godRepository.findByName("Ullr");
        Assertions.assertFalse(result.isEmpty());
    }
}