package com.akshay.api.smoke;

import com.akshay.api.client.ApiClient;
import com.akshay.api.endpoints.ApiEndpoint;
import com.akshay.api.specifications.ResponseSpecificationBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest {
    @Test
    public void verifyGetSingleUser() {

        Response response = ApiClient.get(ApiEndpoint.USERS);

        response.then()
                .spec(ResponseSpecificationBuilder.getResponseSpecification());

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
