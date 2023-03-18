package org.jnosql.demo.quarkus.mongo.reactive.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

import org.jnosql.demo.quarkus.mongo.reactive.model.Person;

@ApplicationScoped
public class PersonRepository implements ReactivePanacheMongoRepository<Person> {
}
