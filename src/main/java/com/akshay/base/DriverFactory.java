package com.akshay.base;

import com.akshay.constants.FrameworkConstants;
import com.akshay.utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent instantiation
    }

    public static WebDriver initDriver() {

        if (!DriverManager.hasDriver()) {

            String browser = ConfigReader.getProperty(FrameworkConstants.BROWSER)
                    .trim()
                    .toLowerCase();

            WebDriver driver = createDriver(browser);

            configureDriver(driver);

            DriverManager.setDriver(driver);
        }

        return DriverManager.getDriver();
    }

    private static WebDriver createDriver(String browser) {

        switch (browser) {

            case "chrome":
                return new ChromeDriver(getChromeOptions());

            case "firefox":
                return new FirefoxDriver();

            case "edge":
                return new EdgeDriver();

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser +
                                ". Supported browsers are: chrome, firefox, edge.");
        }
    }

    private static void configureDriver(WebDriver driver) {

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(getImplicitWait()));

        driver.manage().window().maximize();
    }

    private static ChromeOptions getChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    private static long getImplicitWait() {

        try {
            return Long.parseLong(
                    ConfigReader.getProperty("timeout.implicit"));
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Invalid value for 'timeout.implicit'. Expected a numeric value.", e);
        }
    }
}