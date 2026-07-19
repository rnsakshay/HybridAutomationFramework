package com.akshay.pages;

import com.akshay.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.akshay.utilities.*;

public class LoginPage extends BasePage {

    private final By txtUsername = By.id("user-name");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.id("login-button");
    private final By lblErrorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter username.
     */
    public LoginPage enterUsername(String username) {
        actions.type(txtUsername, username);
        return this;
    }

    /**
     * Enter password.
     */
    public LoginPage enterPassword(String password) {
        actions.type(txtPassword, password);
        return this;
    }

    /**
     * Click login button.
     */
    public ProductsPage clickLogin() {
        actions.click(btnLogin);
        return new ProductsPage(driver);
    }

    /**
     * Login into application.
     */
    public ProductsPage login(String username, String password) {

        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    /**
     * Returns login error message.
     */
    public String getErrorMessage() {
        return actions.getText(lblErrorMessage);
    }

    /**
     * Checks whether error message is displayed.
     */
    public boolean isErrorDisplayed() {
        return actions.isDisplayed(lblErrorMessage);
    }
}