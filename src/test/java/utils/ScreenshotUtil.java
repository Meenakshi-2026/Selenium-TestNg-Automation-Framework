package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtil {
    private ScreenshotUtil() {}

    // Try to find WebDriver on test instance (field 'driver' or method 'getDriver')
    public static WebDriver getWebDriverFromInstance(ITestResult result) {
        Object instance = result.getInstance();
        if (instance == null) return null;

        Class<?> cls = instance.getClass();
        while (cls != null) {
            try {
                java.lang.reflect.Field f = cls.getDeclaredField("driver");
                f.setAccessible(true);
                Object val = f.get(instance);
                if (val instanceof WebDriver) return (WebDriver) val;
            } catch (NoSuchFieldException ignored) {
                // try superclass
                cls = cls.getSuperclass();
            } catch (Exception ignored) {
                break;
            }
        }

        try {
            java.lang.reflect.Method m = instance.getClass().getMethod("getDriver");
            Object r = m.invoke(instance);
            if (r instanceof WebDriver) return (WebDriver) r;
        } catch (Exception ignored) {
        }

        return null;
    }

    // Capture screenshot and return the saved path (string) or null on failure
    public static String capture(WebDriver driver, String baseName) {
        if (driver == null) return null;
        try {
            // Prefer saving screenshots inside the report folder so image paths are relative to report HTML
            String reportDir = ConfigReader.get("report.path");
            if (reportDir == null || reportDir.isEmpty()) reportDir = "test-reports";
            if (reportDir.endsWith("/") || reportDir.endsWith("\\")) {
                reportDir = reportDir.substring(0, reportDir.length() - 1);
            }

            Path destDir = Paths.get(reportDir).resolve("screenshots");
            Files.createDirectories(destDir);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = baseName + "_" + timestamp + ".png";
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = destDir.resolve(fileName);
            Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);

            // Return path relative to report file (so the HTML can load it)
            return "screenshots/" + fileName;
        } catch (Exception e) {
            return null;
        }
    }
}
