package org.jnosql.demo.quarkus.mongo.panache;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.jnosql.demo.quarkus.mongo.panache.God;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class GodResourceTest {

    @Test
    public void testGodCreationEndpoint() {
        with()
            .contentType(ContentType.JSON)
            .body(new God("TestGod", "TestPower"))
            .when()
            .post("/gods")
            .then()
            .statusCode(201);
    }

    @Test
    public void testGodGetAllEndpoint() {
        given()
            .when().get("/gods")
            .then()
            .statusCode(200);
    }

    /**
     * It's necessary to put an id that is existent, otherwise the test will fail.
     */
    // @Test
    public void testDeleteGodEndpoint() {
        given()
            .header("Content-type", "application/json")
            .when()
            .delete("/gods/5fdcc4609af7fa401315ada2")
            .then()
            .statusCode(204);
    }

}