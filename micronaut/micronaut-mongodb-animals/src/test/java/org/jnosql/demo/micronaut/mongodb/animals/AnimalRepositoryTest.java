package org.jnosql.demo.micronaut.mongodb.animals;

import io.micronaut.test.annotation.MicronautTest;
import java.util.List;
import javax.inject.Inject;

import org.jnosql.demo.micronaut.mongodb.animals.Animal;
import org.jnosql.demo.micronaut.mongodb.animals.AnimalRepository;
import org.jnosql.demo.micronaut.mongodb.animals.Cat;
import org.jnosql.demo.micronaut.mongodb.animals.Dog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
class AnimalRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(AnimalRepositoryTest.class);

    @Inject
    AnimalRepository animalRepository;

    @Test
    @Order(1)
    void insertThenRead() {

        Animal cat = animalRepository.insert(new Cat());
        Animal dog = animalRepository.insert(new Dog());
        List<Animal> catList = animalRepository.findByType(Cat.TYPE);
        List<Animal> dogList = animalRepository.findByType(Dog.TYPE);
        log.info("{} cats and {} dogs in the DB", catList.size(), dogList.size());
        Assertions.assertFalse(catList.isEmpty());
        Assertions.assertFalse(dogList.isEmpty());
    }

    @Test
    @Order(2)
    void readCatWithoutInsertingFirst() {
        List<Animal> catList = animalRepository.findByType(Cat.TYPE);
        Assertions.assertFalse(catList.isEmpty());
    }

    @Test
    @Order(3)
    void readDogWithoutInsertingFirst() {
        List<Animal> dogList = animalRepository.findByType(Dog.TYPE);
        Assertions.assertFalse(dogList.isEmpty());
    }
}