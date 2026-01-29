package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverUtils;

/**
 * BasePage class - Base class for all page object classes
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverUtils utils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}
