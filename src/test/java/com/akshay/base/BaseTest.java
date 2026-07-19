package com.akshay.base;

import com.akshay.constants.FrameworkConstants;
import com.akshay.utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        logger.info("========== Test Execution Started ==========");

        logger.info("Initializing browser...");
        driver = DriverFactory.initDriver();

        String targetUrl = ConfigReader.getProperty(FrameworkConstants.BASE_URL);

        logger.info("Navigating to URL: {}", targetUrl);
        driver.get(targetUrl);
        logger.info("Application launched successfully.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Closing browser...");
        DriverManager.unloadDriver();
        logger.info("Browser closed successfully.");
        logger.info("========== Test Execution Finished ==========");
    }
}