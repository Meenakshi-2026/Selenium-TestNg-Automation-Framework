package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener - Listens to TestNG events and logs them to ExtentReport
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
        ExtentReportManager.logFail("Test failed: " + result.getMethod().getMethodName());
        ExtentReportManager.logFail("Failure: " + result.getThrowable().getMessage());
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
