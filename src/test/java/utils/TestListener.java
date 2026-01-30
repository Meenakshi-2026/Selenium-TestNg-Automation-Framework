package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Simple TestListener: starts Extent tests and attaches failure screenshots.
 */
public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        ExtentReportManager.startTest(testName, description != null ? description : "");
        ExtentReportManager.logInfo("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.logPass("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = ExtentReportManager.getTest();

        ExtentReportManager.logFail("Test failed: " + methodName);
        ExtentReportManager.logFail("Failure: " + result.getThrowable().getMessage());

        // Simple helper finds driver and captures screenshot
        WebDriver driver = ScreenshotUtil.getWebDriverFromInstance(result);
        if (driver != null && test != null) {
            String path = ScreenshotUtil.capture(driver, methodName);
            if (path != null) {
                test.fail("Failure screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            } else {
                test.fail("Could not capture screenshot");
            }
        } else if (test != null) {
            test.fail("WebDriver instance not available for screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.logSkip("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
    }
}
