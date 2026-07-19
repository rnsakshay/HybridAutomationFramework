package com.akshay.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaScriptUtils {

    private static final Logger logger = LoggerFactory.getLogger(JavaScriptUtils.class);

    private final WebDriver driver;
    private final JavascriptExecutor jsExecutor;
    private final WaitUtils waitUtils;

    public JavaScriptUtils(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Click an element using JavaScript.
     *
     * @param locator Element locator
     */
    public void clickUsingJS(By locator) {
        try {
            WebElement element = waitUtils.waitForVisibility(locator);
            jsExecutor.executeScript("arguments[0].click();", element);
            logger.info("Clicked on element using JavaScript: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click on element using JavaScript: {}", locator, e);
            throw e;
        }
    }

    /**
     * Scroll the page until the element is visible.
     *
     * @param locator Element locator
     */
    public void scrollIntoView(By locator) {
        try {
            WebElement element = waitUtils.waitForVisibility(locator);
            jsExecutor.executeScript(
                    "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                    element
            );
            logger.info("Scrolled to element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Scroll to the top of the page.
     */
    public void scrollToTop() {
        try {
            jsExecutor.executeScript("window.scrollTo(0,0);");
            logger.info("Scrolled to top of the page.");
        } catch (Exception e) {
            logger.error("Failed to scroll to top.", e);
            throw e;
        }
    }

    /**
     * Scroll to the bottom of the page.
     */
    public void scrollToBottom() {
        try {
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            logger.info("Scrolled to bottom of the page.");
        } catch (Exception e) {
            logger.error("Failed to scroll to bottom.", e);
            throw e;
        }
    }

    /**
     * Scroll the page by specified x and y coordinates.
     *
     * @param x Horizontal pixels
     * @param y Vertical pixels
     */
    public void scrollBy(int x, int y) {
        try {
            jsExecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
            logger.info("Scrolled page by x: {}, y: {}", x, y);
        } catch (Exception e) {
            logger.error("Failed to scroll page.", e);
            throw e;
        }
    }

    /**
     * Highlight an element using a red border.
     *
     * @param locator Element locator
     */
    public void highlightElement(By locator) {
        try {
            WebElement element = waitUtils.waitForVisibility(locator);

            jsExecutor.executeScript(
                    "arguments[0].style.border='3px solid red';",
                    element
            );

            logger.info("Highlighted element: {}", locator);

        } catch (Exception e) {
            logger.error("Failed to highlight element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Refresh the current page using JavaScript.
     */
    public void refreshPage() {
        try {
            jsExecutor.executeScript("history.go(0);");
            logger.info("Page refreshed successfully.");
        } catch (Exception e) {
            logger.error("Failed to refresh page.", e);
            throw e;
        }
    }

    /**
     * Set an attribute value using JavaScript.
     *
     * @param locator   Element locator
     * @param attribute Attribute name
     * @param value     Attribute value
     */
    public void setAttribute(By locator, String attribute, String value) {

        try {
            WebElement element = waitUtils.waitForVisibility(locator);

            jsExecutor.executeScript(
                    "arguments[0].setAttribute(arguments[1], arguments[2]);",
                    element,
                    attribute,
                    value
            );

            logger.info("Set attribute '{}'='{}' on element: {}", attribute, value, locator);

        } catch (Exception e) {
            logger.error("Failed to set attribute '{}' on element: {}", attribute, locator, e);
            throw e;
        }
    }

    /**
     * Remove an attribute using JavaScript.
     *
     * @param locator Element locator
     * @param attribute Attribute name
     */
    public void removeAttribute(By locator, String attribute) {

        try {
            WebElement element = waitUtils.waitForVisibility(locator);

            jsExecutor.executeScript(
                    "arguments[0].removeAttribute(arguments[1]);",
                    element,
                    attribute
            );

            logger.info("Removed attribute '{}' from element: {}", attribute, locator);

        } catch (Exception e) {
            logger.error("Failed to remove attribute '{}' from element: {}", attribute, locator, e);
            throw e;
        }
    }

    /**
     * Returns current page title using JavaScript.
     *
     * @return Page title
     */
    public String getPageTitle() {

        try {

            String title = (String) jsExecutor.executeScript("return document.title;");

            logger.info("Current page title: {}", title);

            return title;

        } catch (Exception e) {

            logger.error("Failed to get page title.", e);

            throw e;
        }
    }

    /**
     * Returns current page URL using JavaScript.
     *
     * @return Current page URL
     */
    public String getCurrentURL() {

        try {

            String url = (String) jsExecutor.executeScript("return document.URL;");

            logger.info("Current page URL: {}", url);

            return url;

        } catch (Exception e) {

            logger.error("Failed to get current URL.", e);

            throw e;
        }
    }

    /**
     * Execute any JavaScript.
     *
     * @param script JavaScript
     * @param args Arguments
     * @return Result
     */
    public Object executeScript(String script, Object... args) {

        try {

            logger.info("Executing JavaScript: {}", script);

            return jsExecutor.executeScript(script, args);

        } catch (Exception e) {

            logger.error("JavaScript execution failed.", e);

            throw e;
        }
    }

    public String getReadyState() {
        return (String) jsExecutor.executeScript("return document.readyState;");
    }

    public void scrollElementToCenter(By locator) {

        WebElement element = waitUtils.waitForVisibility(locator);

        jsExecutor.executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                element
        );
    }
}