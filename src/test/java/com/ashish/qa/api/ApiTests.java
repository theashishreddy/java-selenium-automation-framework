package com.ashish.qa.api;

import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests {
    @BeforeClass
    public void startApi() throws Exception {
        int port = LocalApiServer.start();
        RestAssured.baseURI = "http://127.0.0.1";
        RestAssured.port = port;
    }

    @Test
    public void healthEndpointShouldReturnUp() {
        given()
            .when().get("/health")
            .then().statusCode(200)
            .contentType("application/json")
            .body("status", equalTo("UP"));
    }

    @Test
    public void userEndpointShouldReturnExpectedUser() {
        given()
            .when().get("/users/1")
            .then().statusCode(200)
            .body("id", equalTo(1))
            .body("name", equalTo("Ashish Demo"))
            .body("role", equalTo("QA"));
    }

    @AfterClass(alwaysRun = true)
    public void stopApi() {
        LocalApiServer.stop();
        RestAssured.reset();
    }
}
