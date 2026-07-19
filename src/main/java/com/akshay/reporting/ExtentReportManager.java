package com.akshay.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportManager {

    private static ExtentReports extentReports;

    private ExtentReportManager() {
    }

    public static ExtentReports getReport() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Hybrid Automation Framework");

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Framework", "Hybrid Automation");
            extentReports.setSystemInfo("Automation Engineer", "Akshay");
            extentReports.setSystemInfo("Java", System.getProperty("java.version"));
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        }

        return extentReports;
    }
}
