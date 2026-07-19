package com.akshay.ui;

import com.akshay.base.BaseTest;
import com.akshay.dataProvider.TestDataProvider;
import com.akshay.pages.LoginPage;
import com.akshay.pages.ProductsPage;
import com.akshay.reporting.ExtentTestManager;
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

        ExtentTestManager.getTest().info("========== Login Test Started ==========");
        ExtentTestManager.getTest().info("Username: " +username);
        ExtentTestManager.getTest()
                .info("Password: " + (password.isEmpty() ? "<EMPTY>" : "******"));
        ExtentTestManager.getTest().info("Expected Result: " +expectedResult);
        ExtentTestManager.getTest().info("Expected Message: " +expectedMessage);

        LoginPage loginPage = new LoginPage(driver);

        ExtentTestManager.getTest().info("Attempting login...");
        loginPage.login(username, password);

        if ("SUCCESS".equalsIgnoreCase(expectedResult)) {

            ExtentTestManager.getTest().info("Verifying successful login...");

            ProductsPage productsPage = new ProductsPage(driver);

            Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page is not displayed after successful login.");

            ExtentTestManager.getTest().info("Login successful. Products page displayed.");

        } else {

            ExtentTestManager.getTest().info("Verifying failed login...");

            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Expected error message is not displayed.");

            String actualMessage = loginPage.getErrorMessage();

            ExtentTestManager.getTest().info("Actual Error Message   : " +actualMessage);
            ExtentTestManager.getTest().info("Expected Error Message : " +expectedMessage);

            Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch.");

            ExtentTestManager.getTest().info("Error message verified successfully.");
        }

        ExtentTestManager.getTest().info("========== Login Test Completed ==========");
    }
}