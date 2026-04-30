package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import base.BaseTest;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = BaseTest.getDriver();

        String testName = result.getName();

        String path = ScreenshotUtil.captureScreenshot(driver, testName);

        test.fail("Test Failed");

        try {
            test.fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}