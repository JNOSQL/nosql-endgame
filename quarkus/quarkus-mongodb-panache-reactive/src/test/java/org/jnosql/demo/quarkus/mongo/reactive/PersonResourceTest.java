package org.jnosql.demo.quarkus.mongo.reactive;

import io.quarkus.test.junit.QuarkusTest;

import org.jnosql.demo.quarkus.mongo.reactive.model.Person;
import org.jnosql.demo.quarkus.mongo.reactive.model.Status;
import org.jnosql.demo.quarkus.mongo.reactive.repository.PersonRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@QuarkusTest
@TestInstance(PER_CLASS)
class PersonResourceTest {

    @Inject
    PersonRepository personRepository;

    private Person person1;
    private Person person2;
    private Person personToDelete;

    @BeforeAll
    public void setup() {
    	personRepository.deleteAll().await().atMost(Duration.ofSeconds(2));

        person1 = new Person();
        person1.name = "Person 1";
        person1.status = Status.ALIVE;

        person2 = new Person();
        person2.name = "Person 2";
        person2.status = Status.ALIVE;

        personToDelete = new Person();
        personToDelete.name = "Person To Delete";
        personToDelete.status = Status.ALIVE;

        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(personToDelete);

        personRepository.persist(people).await().atMost(Duration.ofSeconds(2));
    }

    @AfterAll
    public void tearDown() {
        personRepository.deleteAll().await().atMost(Duration.ofSeconds(2));
    }

    @Test
    void testGetAllEndpoint() {
        given()
                .when().get("/persons")
                .then()
                .statusCode(200)
                .body("$.size()", is(2));
    }

    @Test
    void testGetByIdEndpoint() {
        given()
                .when().get("/persons/{id}", person1.id.toString())
                .then()
                .statusCode(200)
                .body("name", is("Person 1"));
    }

    @Test
    void testCreateEndpoint() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "Person 3");
        payload.put("status", "ALIVE");

        given()
                .body(payload)
                .contentType(JSON)
                .when().post("/persons")
                .then()
                .statusCode(201)
                .body("name", is("Person 3"));
    }

    @Test
    void testUpdateEndpoint() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "Person 2 Updated");
        payload.put("status", "ALIVE");

        given()
                .body(payload)
                .contentType(JSON)
                .when().put("/persons/{id}", person2.id.toString())
                .then()
                .statusCode(200)
                .body("name", is("Person 2 Updated"));
    }

    @Test
    void testDeleteEndpoint() {
        given()
                .contentType(JSON)
                .when().delete("/persons/{id}", personToDelete.id.toString())
                .then()
                .statusCode(200);
    }

}
