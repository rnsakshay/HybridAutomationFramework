package com.akshay.utilities;

import com.akshay.base.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {
    private static final Logger logger =  LoggerFactory.getLogger(ScreenshotUtils.class);

    private static final String SCREENSHOT_DIRECTORY =
            System.getProperty("user.dir")
                    + File.separator
                    + "reports"
                    + File.separator
                    + "screenshots";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {}

    public static String captureScreenshot(String testName) {

        WebDriver driver = DriverManager.getDriver();

        String timestamp = LocalDateTime.now().format(FORMATTER);

        String screenshotPath =
                SCREENSHOT_DIRECTORY
                        + File.separator
                        + testName
                        + "_"
                        + timestamp
                        + ".png";

        try {

            File directory = new File(SCREENSHOT_DIRECTORY);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            File destination = new File(screenshotPath);

            FileUtils.copyFile(source, destination);

            logger.info("Screenshot captured successfully : {}", screenshotPath);

            return screenshotPath;

        } catch (IOException e) {

            logger.error("Unable to capture screenshot.", e);

            throw new RuntimeException("Screenshot capture failed.", e);
        }
    }
}

