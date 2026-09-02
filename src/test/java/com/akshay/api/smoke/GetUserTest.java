package com.akshay.api.smoke;

import com.akshay.api.client.ApiClient;
import com.akshay.api.endpoints.ApiEndpoint;
import com.akshay.api.specifications.ResponseSpecificationBuilder;
import com.akshay.reporting.ExtentTestManager;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest {

    private static final Logger log = LoggerFactory.getLogger(GetUserTest.class);

    @Test
    public void verifyGetUsers() {
        ExtentTestManager.getTest().info("Starting test: Verify GET users API");
        ExtentTestManager.getTest()
                .info("Sending GET request to endpoint: " + ApiEndpoint.USERS);
        Response response = ApiClient.get(ApiEndpoint.USERS);
        ExtentTestManager.getTest()
                .info("Received response with status code: " + response.getStatusCode());
        ExtentTestManager.getTest().info("Validating response specification");
        response.then()
                .spec(ResponseSpecificationBuilder.getResponseSpecification());
        ExtentTestManager.getTest()
                .info("Validating response status code is 200");
        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected status code 200 but received " + response.getStatusCode()
        );
        ExtentTestManager.getTest()
                .pass("GET users API test passed successfully");
    }
}