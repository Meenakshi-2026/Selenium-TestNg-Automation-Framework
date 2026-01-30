package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExtentReportManager - Manages Extent Report generation and logging
 */
public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Initialize ExtentReports
     */
    public static void initReport() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
            String reportDir = "test-reports";
            try {
                String cfg = ConfigReader.get("report.path");
                if (cfg != null && !cfg.isEmpty()) reportDir = cfg.replaceAll("\\\\$", "");
            } catch (Exception ignored) {}

            // ensure directory ends without separator
            if (reportDir.endsWith("/") || reportDir.endsWith("\\")) {
                reportDir = reportDir.substring(0, reportDir.length() - 1);
            }

            String reportPath = reportDir + "/ExtentReport_" + timestamp + ".html";

            // Create directories if they don't exist
            new File(reportDir).mkdirs();

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("TestNG Selenium Test Report");
            reporter.config().setReportName("Test Execution Report");
            
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", "Chrome");
        }
    }

    /**
     * Create a new test in the report
     */
    public static void startTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
    }

    /**
     * Get the current ExtentTest instance
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Log information
     */
    public static void logInfo(String message) {
        if (test.get() != null) {
            test.get().info(message);
        }
    }

    /**
     * Log pass
     */
    public static void logPass(String message) {
        if (test.get() != null) {
            test.get().pass(message);
        }
    }

    /**
     * Log fail
     */
    public static void logFail(String message) {
        if (test.get() != null) {
            test.get().fail(message);
        }
    }

    /**
     * Log warning
     */
    public static void logWarning(String message) {
        if (test.get() != null) {
            test.get().warning(message);
        }
    }

    /**
     * Log skip
     */
    public static void logSkip(String message) {
        if (test.get() != null) {
            test.get().skip(message);
        }
    }

    /**
     * Flush the report
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    /**
     * End the current test
     */
    public static void endTest() {
        test.remove();
    }
}
