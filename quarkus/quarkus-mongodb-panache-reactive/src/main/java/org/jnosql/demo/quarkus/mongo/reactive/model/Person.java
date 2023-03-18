package org.jnosql.demo.quarkus.mongo.reactive.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import static org.jnosql.demo.quarkus.mongo.reactive.model.Status.ALIVE;

import java.time.LocalDate;

@MongoEntity(collection="people")
public class Person {
    public ObjectId id;
    public String name;

    @BsonProperty("birth")
    public LocalDate birthDate = LocalDate.now();

    public Status status = ALIVE;
}
