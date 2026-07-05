package com.akshay.base;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    public static final ThreadLocal<WebDriver> driverThreadLocal =
            new ThreadLocal<>();

    private DriverManager() {

    }
    public static WebDriver getDriver(){
        WebDriver driver = driverThreadLocal.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized for the current thread.");
        }

        return driver;
    };

    public static void setDriver(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null.");
        }

        driverThreadLocal.set(driver);
    }

    public static void unloadDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

    public static boolean hasDriver() {
        return driverThreadLocal.get() != null;
    }

}
