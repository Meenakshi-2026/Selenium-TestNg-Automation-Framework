package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * WebDriverUtils class - Contains common Selenium WebDriver utility methods
 */
public class WebDriverUtils {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_WAIT_TIME = 10;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
    }

    /**
     * Wait for element to be visible
     */
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Click on element
     */
    public void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    /**
     * Type text in element
     */
    public void sendKeys(By locator, String text) {
        waitForElementToBeVisible(locator).sendKeys(text);
    }

    /**
     * Get text from element
     */
    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    /**
     * Select dropdown by visible text
     */
    public void selectDropdownByVisibleText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /**
     * Select dropdown by value
     */
    public void selectDropdownByValue(By locator, String value) {
        WebElement element = waitForElementToBeVisible(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
}
