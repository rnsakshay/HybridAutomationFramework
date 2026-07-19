package com.akshay.listeners;

import com.akshay.reporting.ExtentReportManager;
import com.akshay.reporting.ExtentTestManager;
import com.akshay.utilities.ScreenshotUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.getReport();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTestManager.setTest(
                ExtentReportManager.getReport()
                        .createTest(result.getMethod().getMethodName())
        );

        ExtentTestManager.getTest()
                .log(Status.INFO, "Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest().fail(result.getThrowable());

        try {
            String screenshotPath =
                    ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName());
            ExtentTestManager.getTest()
                    .addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            ExtentTestManager.getTest()
                    .warning("Unable to attach screenshot.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentReportManager.getReport().flush();

        ExtentTestManager.unload();
    }
}