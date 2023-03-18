package org.jnosql.demo.quarkus.mongo.reactive.function;

import org.bson.types.ObjectId;
import org.jnosql.demo.quarkus.mongo.reactive.model.Status;

import java.time.LocalDate;

public record PersonDTO(ObjectId id, String name, LocalDate birthDate, Status status) {}
