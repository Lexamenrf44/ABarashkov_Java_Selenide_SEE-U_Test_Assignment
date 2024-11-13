package com.takamulstg.seeu.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestService {

    @Step("POST {endpoint}")
    public static <T> T postAsDto(String endpoint, Object body, Class<T> dtoClass) {
        return given()
                .auth().oauth2(RestContext.accessToken)
                .body(body)
                .post(endpoint)
                .then()
                .extract().body().as(dtoClass);
    }

    @Step("GET {endpoint}")
    public static <T> T getAsDto(String endpoint, Class<T> dtoClass) {
        return given()
                .auth().oauth2(RestContext.accessToken)
                .get(endpoint)
                .then()
                .extract().body().as(dtoClass);
    }

    @Step("POST {endpoint}")
    public static Response postAsResponse(String endpoint, Object body) {
        return given()
                .auth().oauth2(RestContext.accessToken)
                .body(body)
                .post(endpoint)
                .then()
                .extract().response();
    }

    @Step("GET {endpoint}")
    public static <T> List<T> postAsListItems(String endpoint,
                                              Object body,
                                              Class<T> dtoClass) {
        return given()
                .auth().oauth2(RestContext.accessToken)
                .body(body)
                .post(endpoint)
                .then()
                .extract().body().jsonPath().getList("items", dtoClass);
    }

    @Step("GET {endpoint}")
    public static <T> List<T> postAsList(String endpoint,
                                              Object body,
                                              Class<T> dtoClass) {
        return given()
                .auth().oauth2(RestContext.accessToken)
                .body(body)
                .post(endpoint)
                .then()
                .extract().body().jsonPath().getList(".", dtoClass);
    }
}
