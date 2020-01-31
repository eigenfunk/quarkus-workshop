package org.example.exercise4;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GreetingResourceTest {

    @Test
    @Order(0)
    public void testGetOne() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .pathParam("id", 1)
                .when().get("/greeting/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is("Salut"));
    }

    @Test
    @Order(1)
    public void testUpdatingAnItem() {
        String name = "Gretchen";
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .body(name)
                .pathParam("id", 1)
                .when()
                .put("/greeting/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(is(name));
    }

    @Test
    @Order(2)
    public void getGetAll() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().get("/greeting")
                .then()
                .statusCode(200)
                .log().body()
                .body("size()", is(4));

    }

    @Test
    @Order(3)
    public void testAddingAnItem() {
        String name = "Hannes";
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .body(name)
                .when()
                .post("/greeting")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(is(name));

        given()
                .when()
                .get("/greeting")
                .then()
                .body("size()", is(5));

    }

    @Test
    @Order(4)
    public void testDeletingAnItem() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .pathParam("id", 3)
                .when()
                .delete("/greeting/{id}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        given()
                .when()
                .get("/greeting")
                .then()
                .body("size()", is(4));
    }
}
