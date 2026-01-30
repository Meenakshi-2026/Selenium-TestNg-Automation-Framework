package utils;

import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * BaseTest class - Contains common setup and teardown methods for all test classes
 */
@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void initReport() {
        ExtentReportManager.initReport();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        ExtentReportManager.logInfo("Browser launched: " + browser);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            ExtentReportManager.logInfo("Browser closed");
        }
        ExtentReportManager.endTest();
        ExtentReportManager.flushReport();
    }

    /**
     * Navigate to URL
     */
    public void navigateToURL(String url) {
        driver.navigate().to(url);
        ExtentReportManager.logInfo("Navigated to URL: " + url);
    }
}
