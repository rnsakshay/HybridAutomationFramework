package com.akshay.ui.smoke;

import com.akshay.base.BaseTest;
import com.akshay.base.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(description = "Verify that the Juice Shop application landing page opens successfully.")
    public void verifyApplicationTitle() {
        String actualTitle = DriverManager.getDriver().getTitle();
        System.out.println(">>> Target Website Title Captured: " + actualTitle);

        // Ensure title is loaded and not null/empty
        Assert.assertNotNull(actualTitle, "Page title is null!");
        Assert.assertFalse(actualTitle.isBlank(), "Page title is completely empty!");
    }
}