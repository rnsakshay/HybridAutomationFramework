package com.akshay.ui.smoke;

import com.akshay.base.BaseTest;
import com.akshay.base.DriverManager;
import com.akshay.reporting.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(description = "Verify that the Juice Shop application landing page opens successfully.")
    public void verifyApplicationTitle() {
        final String expectedTitle = "Swag Labs";
        ExtentTestManager.getTest().info("Verifying application landing page title.");
        String actualTitle = DriverManager.getDriver().getTitle();
        ExtentTestManager.getTest().info("Captured page title: " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle,
                "Application title does not match.");

        ExtentTestManager.getTest().pass("Application title verified successfully.");
    }
}