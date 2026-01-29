package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * LoginPage class - Page Object Model for Login Page
 * URL: https://www.saucedemo.com/
 */
public class LoginPage extends BasePage {
    
    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By errorContainer = By.cssSelector(".error-message-container");
    private By logoImage = By.className("login_logo");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify login page is loaded
     */
    public boolean isLoginPageLoaded() {
        return utils.isElementDisplayed(logoImage);
    }

    /**
     * Enter username
     */
    public void enterUsername(String username) {
        utils.sendKeys(usernameField, username);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        utils.sendKeys(passwordField, password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        utils.click(loginButton);
    }

    /**
     * Perform login with username and password
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        try {
            return utils.getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Verify error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return utils.isElementDisplayed(errorMessage);
    }

    /**
     * Get username field value
     */
    public String getUsernameValue() {
        return driver.findElement(usernameField).getAttribute("value");
    }

    /**
     * Get password field value
     */
    public String getPasswordValue() {
        return driver.findElement(passwordField).getAttribute("value");
    }

    /**
     * Clear username field
     */
    public void clearUsername() {
        driver.findElement(usernameField).clear();
    }

    /**
     * Clear password field
     */
    public void clearPassword() {
        driver.findElement(passwordField).clear();
    }

    /**
     * Clear all fields
     */
    public void clearAllFields() {
        clearUsername();
        clearPassword();
    }

    /**
     * Get login page title
     */
    public String getLoginPageTitle() {
        return getPageTitle();
    }

    /**
     * Verify login page URL
     */
    public boolean isLoginPageURL() {
        String currentURL = getCurrentURL();
        return currentURL.contains("saucedemo.com");
    }
}
