package com.akshay.ui;

import com.akshay.base.BaseTest;
import com.akshay.dataProvider.TestDataProvider;
import com.akshay.pages.LoginPage;
import com.akshay.pages.ProductsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void verifyLogin(String username,
                            String password,
                            String expectedResult,
                            String expectedMessage) {

        logger.info("========== Login Test Started ==========");
        logger.info("Username: [{}]", username);
        logger.info("Password: [{}]", password.isEmpty() ? "<EMPTY>" : "******");
        logger.info("Expected Result: {}", expectedResult);
        logger.info("Expected Message: {}", expectedMessage);

        LoginPage loginPage = new LoginPage(driver);

        logger.info("Attempting login...");
        loginPage.login(username, password);

        if ("SUCCESS".equalsIgnoreCase(expectedResult)) {

            logger.info("Verifying successful login...");

            ProductsPage productsPage = new ProductsPage(driver);

            Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page is not displayed after successful login.");

            logger.info("Login successful. Products page displayed.");

        } else {

            logger.info("Verifying failed login...");

            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Expected error message is not displayed.");

            String actualMessage = loginPage.getErrorMessage();

            logger.info("Actual Error Message   : {}", actualMessage);
            logger.info("Expected Error Message : {}", expectedMessage);

            Assert.assertEquals(actualMessage, expectedMessage,
                    "Error message mismatch.");

            logger.info("Error message verified successfully.");
        }

        logger.info("========== Login Test Completed ==========");
    }
}