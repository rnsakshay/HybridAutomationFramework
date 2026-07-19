package com.akshay.base;

import com.akshay.constants.FrameworkConstants;
import com.akshay.reporting.ExtentTestManager;
import com.akshay.utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.akshay.listeners.TestListener;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        logger.info("Initializing browser...");
        driver = DriverFactory.initDriver();
        String targetUrl = ConfigReader.getProperty(FrameworkConstants.BASE_URL);
        logger.info("Navigating to URL: {}", targetUrl);
        driver.get(targetUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Closing browser...");
        DriverManager.unloadDriver();
    }
}