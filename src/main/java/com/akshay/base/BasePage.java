package com.akshay.base;

import com.akshay.utilities.ElementActions;
import com.akshay.utilities.JavaScriptUtils;
import com.akshay.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;
    protected ElementActions actions;
    protected JavaScriptUtils jsUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.actions = new ElementActions(driver);
        this.jsUtils = new JavaScriptUtils(driver);
    }
}