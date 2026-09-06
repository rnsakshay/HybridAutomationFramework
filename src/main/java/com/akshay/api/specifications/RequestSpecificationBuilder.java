package com.akshay.api.specifications;

import com.akshay.constants.FrameworkConstants;
import com.akshay.utilities.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecificationBuilder {

    private RequestSpecificationBuilder() {
    }

    public static RequestSpecification getRequestSpecification() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty(FrameworkConstants.API_BASE_URL))
                .addHeader("x-api-key", getApiKey())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    private static String getApiKey() {

        String apiKey = System.getProperty("api.key");

        if (apiKey == null || apiKey.isBlank()) {
            apiKey = System.getenv("REQRES_API_KEY");
        }

        if (apiKey == null || apiKey.isBlank()) {
            apiKey = ConfigReader.getProperty(FrameworkConstants.API_KEY);
        }

        return apiKey.trim();
    }
}
