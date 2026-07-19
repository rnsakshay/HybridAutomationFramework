package com.akshay.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WaitUtils {

    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {

        this.driver = driver;

        int explicitWait = Integer.parseInt(
                ConfigReader.getProperty("timeout.explicit")
        );

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    }

    /**
     * Wait until the element is visible.
     */
    public WebElement waitForVisibility(By locator) {

        logger.info("Waiting for visibility of element: {}", locator);

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element not visible within timeout: {}", locator);
            throw e;
        }
    }

    /**
     * Wait until the element is clickable.
     */
    public WebElement waitForClickable(By locator) {

        logger.info("Waiting for element to be clickable: {}", locator);

        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            logger.error("Element not clickable within timeout: {}", locator);
            throw e;
        }
    }

    /**
     * Wait until the element is present in DOM.
     */
    public WebElement waitForPresence(By locator) {

        logger.info("Waiting for presence of element: {}", locator);

        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element not present within timeout: {}", locator);
            throw e;
        }
    }

    /**
     * Wait until the element disappears.
     */
    public boolean waitForInvisibility(By locator) {

        logger.info("Waiting for element to disappear: {}", locator);

        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element still visible after timeout: {}", locator);
            throw e;
        }
    }

    /**
     * Wait until page title contains expected text.
     */
    public boolean waitForTitleContains(String title) {

        logger.info("Waiting for title to contain: {}", title);

        return wait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Wait until URL contains expected text.
     */
    public boolean waitForUrlContains(String url) {

        logger.info("Waiting for URL to contain: {}", url);

        return wait.until(ExpectedConditions.urlContains(url));
    }

    /**
     * Wait until page is completely loaded.
     */
    public void waitForPageLoad() {

        logger.info("Waiting for page to load completely.");

        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete"));
    }
}