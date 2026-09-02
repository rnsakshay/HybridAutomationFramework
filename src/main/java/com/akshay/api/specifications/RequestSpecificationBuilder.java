package com.akshay.api.specifications;

import com.akshay.constants.FrameworkConstants;
import com.akshay.utilities.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecificationBuilder {

    private RequestSpecificationBuilder() {
    }

    public static RequestSpecification getRequestSpecification() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty(FrameworkConstants.API_BASE_URL))
                .addHeader("x-api-key", "reqres_dcd611de310b44aba1bb9afcae144a05")
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .build();
    }
}