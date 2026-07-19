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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String destination = System.getProperty("user.dir")
                + "/test-output/screenshots/"
                + testName + "_" + timestamp + ".png";

        File source = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            throw new RuntimeException("Unable to capture screenshot", e);
        }

        return destination;
    }
}

