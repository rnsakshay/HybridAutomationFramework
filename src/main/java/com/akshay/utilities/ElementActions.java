package com.akshay.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.stream.Collectors;

public class ElementActions {

    private static final Logger logger = LoggerFactory.getLogger(ElementActions.class);

    private final WebDriver driver;
    private final WaitUtils waitUtils;
    private final Actions actions;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.actions = new Actions(driver);
    }

    /**
     * Click on an element.
     */
    public void click(By locator) {
        try {
            waitUtils.waitForClickable(locator).click();
            logger.info("Clicked on element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Type text into an input field.
     */
    public void type(By locator, String text) {
        try {
            WebElement element = waitUtils.waitForVisibility(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Entered text '{}' into element: {}", text, locator);
        } catch (Exception e) {
            logger.error("Failed to enter text into element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Clear an input field.
     */
    public void clear(By locator) {
        try {
            waitUtils.waitForVisibility(locator).clear();
            logger.info("Cleared element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to clear element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Submit a form.
     */
    public void submit(By locator) {
        try {
            waitUtils.waitForVisibility(locator).submit();
            logger.info("Submitted element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to submit element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Get visible text.
     */
    public String getText(By locator) {
        return waitUtils.waitForVisibility(locator).getText();
    }

    /**
     * Get attribute value.
     */
    public String getAttribute(By locator, String attribute) {
        return waitUtils.waitForVisibility(locator).getAttribute(attribute);
    }

    /**
     * Get CSS property value.
     */
    public String getCssValue(By locator, String property) {
        return waitUtils.waitForVisibility(locator).getCssValue(property);
    }

    /**
     * Check if element is displayed.
     */
    public boolean isDisplayed(By locator) {
        return waitUtils.waitForVisibility(locator).isDisplayed();
    }

    /**
     * Check if element is enabled.
     */
    public boolean isEnabled(By locator) {
        return waitUtils.waitForVisibility(locator).isEnabled();
    }

    /**
     * Check if element is selected.
     */
    public boolean isSelected(By locator) {
        return waitUtils.waitForVisibility(locator).isSelected();
    }

    /**
     * Return single WebElement.
     */
    public WebElement getElement(By locator) {
        return waitUtils.waitForVisibility(locator);
    }

    /**
     * Return list of WebElements.
     */
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Hover mouse over an element.
     */
    public void hover(By locator) {
        try {
            WebElement element = waitUtils.waitForVisibility(locator);
            actions.moveToElement(element).perform();
            logger.info("Hovered over element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to hover over element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Double-click on an element.
     */
    public void doubleClick(By locator) {
        try {
            WebElement element = waitUtils.waitForClickable(locator);
            actions.doubleClick(element).perform();
            logger.info("Double clicked on element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to double click on element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Right-click on an element.
     */
    public void rightClick(By locator) {
        try {
            WebElement element = waitUtils.waitForClickable(locator);
            actions.contextClick(element).perform();
            logger.info("Right clicked on element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to right click on element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Drag an element and drop it on another element.
     */
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        try {
            WebElement source = waitUtils.waitForVisibility(sourceLocator);
            WebElement target = waitUtils.waitForVisibility(targetLocator);

            actions.dragAndDrop(source, target).perform();

            logger.info("Dragged element {} to {}", sourceLocator, targetLocator);

        } catch (Exception e) {
            logger.error("Failed to drag and drop from {} to {}", sourceLocator, targetLocator, e);
            throw e;
        }
    }

    /**
     * Move mouse to an element.
     */
    public void moveToElement(By locator) {
        try {
            WebElement element = waitUtils.waitForVisibility(locator);
            actions.moveToElement(element).perform();
            logger.info("Moved to element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to move to element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Select dropdown option by visible text.
     */
    public void selectByVisibleText(By locator, String text) {
        try {
            Select select = new Select(waitUtils.waitForVisibility(locator));
            select.selectByVisibleText(text);
            logger.info("Selected '{}' from dropdown: {}", text, locator);
        } catch (Exception e) {
            logger.error("Failed to select '{}' from dropdown: {}", text, locator, e);
            throw e;
        }
    }

    /**
     * Select dropdown option by index.
     */
    public void selectByIndex(By locator, int index) {
        try {
            Select select = new Select(waitUtils.waitForVisibility(locator));
            select.selectByIndex(index);
            logger.info("Selected index '{}' from dropdown: {}", index, locator);
        } catch (Exception e) {
            logger.error("Failed to select index '{}' from dropdown: {}", index, locator, e);
            throw e;
        }
    }

    /**
     * Returns selected option text.
     */
    public String getSelectedOption(By locator) {
        Select select = new Select(waitUtils.waitForVisibility(locator));
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Returns all dropdown option texts.
     */
    public List<String> getAllDropdownOptions(By locator) {
        Select select = new Select(waitUtils.waitForVisibility(locator));

        return select.getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Press ENTER key.
     */
    public void pressEnter(By locator) {
        try {
            waitUtils.waitForVisibility(locator).sendKeys(Keys.ENTER);
            logger.info("Pressed ENTER on element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to press ENTER on element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Press TAB key.
     */
    public void pressTab(By locator) {
        try {
            waitUtils.waitForVisibility(locator).sendKeys(Keys.TAB);
            logger.info("Pressed TAB on element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to press TAB on element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Press ESCAPE key.
     */
    public void pressEscape(By locator) {
        try {
            waitUtils.waitForVisibility(locator).sendKeys(Keys.ESCAPE);
            logger.info("Pressed ESCAPE on element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to press ESCAPE on element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Send keyboard key to an element.
     */
    public void sendKey(By locator, Keys key) {
        try {
            waitUtils.waitForVisibility(locator).sendKeys(key);
            logger.info("Sent key '{}' to element: {}", key, locator);
        } catch (Exception e) {
            logger.error("Failed to send key '{}' to element: {}", key, locator, e);
            throw e;
        }
    }

    /**
     * Upload a file.
     */
    public void uploadFile(By locator, String filePath) {
        try {
            waitUtils.waitForVisibility(locator).sendKeys(filePath);
            logger.info("Uploaded file '{}' using element: {}", filePath, locator);
        } catch (Exception e) {
            logger.error("Failed to upload file '{}' using element: {}", filePath, locator, e);
            throw e;
        }
    }

    /**
     * Check a checkbox if not already checked.
     */
    public void check(By locator) {
        WebElement element = waitUtils.waitForClickable(locator);

        if (!element.isSelected()) {
            element.click();
            logger.info("Checkbox checked: {}", locator);
        }
    }

    /**
     * Uncheck a checkbox if checked.
     */
    public void uncheck(By locator) {
        WebElement element = waitUtils.waitForClickable(locator);

        if (element.isSelected()) {
            element.click();
            logger.info("Checkbox unchecked: {}", locator);
        }
    }
}