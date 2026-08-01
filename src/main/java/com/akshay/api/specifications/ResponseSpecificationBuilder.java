package com.akshay.api.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public final class ResponseSpecificationBuilder {

    private ResponseSpecificationBuilder() {
    }

    public static ResponseSpecification getResponseSpecification() {

        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }
}