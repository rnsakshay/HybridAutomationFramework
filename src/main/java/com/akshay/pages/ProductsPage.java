package com.akshay.pages;

import com.akshay.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By lblProducts = By.cssSelector(".title");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsPageDisplayed() {
        return actions.isDisplayed(lblProducts);
    }

    public String getPageTitle() {
        return actions.getText(lblProducts);
    }
}