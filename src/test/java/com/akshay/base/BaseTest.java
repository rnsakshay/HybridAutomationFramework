package com.akshay.base;

import com.akshay.constants.FrameworkConstants;
import com.akshay.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.initDriver();
        String targetUrl = ConfigReader.getProperty(FrameworkConstants.BASE_URL);
        driver.get(targetUrl);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.unloadDriver();
    }
}