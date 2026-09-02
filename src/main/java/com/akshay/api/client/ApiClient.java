package com.akshay.api.client;

import com.akshay.api.endpoints.ApiEndpoint;
import com.akshay.api.specifications.RequestSpecificationBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private ApiClient() {
    }

    public static Response get(ApiEndpoint endpoint) {
        return given()
                .spec(RequestSpecificationBuilder.getRequestSpecification())
                .when()
                .get(endpoint.getPath());
    }

    public static Response post(ApiEndpoint endpoint, Object requestBody) {
        return given()
                .spec(RequestSpecificationBuilder.getRequestSpecification())
                .body(requestBody)
                .when()
                .post(endpoint.getPath());
    }

    public static Response put(ApiEndpoint endpoint, Object requestBody) {
        return given()
                .spec(RequestSpecificationBuilder.getRequestSpecification())
                .body(requestBody)
                .when()
                .put(endpoint.getPath());
    }

    public static Response patch(ApiEndpoint endpoint, Object requestBody) {
        return given()
                .spec(RequestSpecificationBuilder.getRequestSpecification())
                .body(requestBody)
                .when()
                .patch(endpoint.getPath());
    }

    public static Response delete(ApiEndpoint endpoint) {
        return given()
                .spec(RequestSpecificationBuilder.getRequestSpecification())
                .when()
                .delete(endpoint.getPath());
    }
}
