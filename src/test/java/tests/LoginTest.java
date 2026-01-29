package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

/**
 * LoginTest class - Contains positive and negative test cases for login functionality
 * Test URL: https://www.saucedemo.com/
 * Test User: standard_user
 * Test Password: secret_sauce
 */
public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private String validUsername = "standard_user";
    private String validPassword = "secret_sauce";
    private String lockedOutUser = "locked_out_user";
    private String invalidUsername = "invalid_user";
    private String invalidPassword = "invalid_password";
    private String sauceDemoURL = "https://www.saucedemo.com/";

    @BeforeMethod
    @Parameters({"browser"})
    @Override
    public void setUp(String browser) {
        super.setUp(browser);
        navigateToURL(sauceDemoURL);
        loginPage = new LoginPage(driver);
    }

    // ==================== POSITIVE TEST CASES ====================

    /**
     * Test Case 1: Verify user can login with valid credentials
     * Description: User should be able to login with correct username and password
     */
    @Test(description = "Verify user can login with valid credentials", priority = 1)
    public void testLoginWithValidCredentials() {
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        
        loginPage.login(validUsername, validPassword);
        
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isUserLoggedInSuccessfully(), 
                         "User is not logged in successfully");
    }

    /**
     * Test Case 2: Verify user is redirected to products page after successful login
     * Description: After successful login, user should be on inventory.html page
     */
    @Test(description = "Verify user is redirected to products page after login", priority = 2)
    public void testUserRedirectedToProductsPage() {
        loginPage.login(validUsername, validPassword);
        
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.getProductsPageURL().contains("inventory"), 
                         "User is not redirected to products page");
    }

    /**
     * Test Case 3: Verify products are displayed after successful login
     * Description: Products list should be visible after successful login
     */
    @Test(description = "Verify products are displayed after successful login", priority = 3)
    public void testProductsAreDisplayedAfterLogin() {
        loginPage.login(validUsername, validPassword);
        
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.areProductsDisplayed(), 
                         "Products are not displayed");
    }

    /**
     * Test Case 4: Verify page title after successful login
     * Description: Page title should be 'Swag Labs' after successful login
     */
    @Test(description = "Verify page title after successful login", priority = 4)
    public void testPageTitleAfterSuccessfulLogin() {
        loginPage.login(validUsername, validPassword);
        
        productsPage = new ProductsPage(driver);
        String pageTitle = productsPage.getProductPageTitle();
        Assert.assertTrue(pageTitle.contains("Swag Labs"), 
                         "Page title is not correct. Actual: " + pageTitle);
    }

    // ==================== NEGATIVE TEST CASES ====================

    /**
     * Test Case 5: Verify login fails with empty username
     * Description: User should see error message when username is empty
     */
    @Test(description = "Verify login fails with empty username", priority = 5)
    public void testLoginWithEmptyUsername() {
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), 
                         "Expected error message not found");
    }

    /**
     * Test Case 6: Verify login fails with empty password
     * Description: User should see error message when password is empty
     */
    @Test(description = "Verify login fails with empty password", priority = 6)
    public void testLoginWithEmptyPassword() {
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        
        loginPage.enterUsername(validUsername);
        loginPage.clickLoginButton();
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"), 
                         "Expected error message not found");
    }

    /**
     * Test Case 7: Verify login fails with both username and password empty
     * Description: User should see error message when both fields are empty
     */
    @Test(description = "Verify login fails with both fields empty", priority = 7)
    public void testLoginWithBothFieldsEmpty() {
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        
        loginPage.clickLoginButton();
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), 
                         "Expected error message not found");
    }

    /**
     * Test Case 8: Verify login fails with invalid username and valid password
     * Description: User should see error message with invalid username
     */
    @Test(description = "Verify login fails with invalid username", priority = 8)
    public void testLoginWithInvalidUsername() {
        loginPage.login(invalidUsername, validPassword);
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match any user in this service"), 
                         "Expected error message not found");
    }

    /**
     * Test Case 9: Verify login fails with valid username and invalid password
     * Description: User should see error message with invalid password
     */
    @Test(description = "Verify login fails with invalid password", priority = 9)
    public void testLoginWithInvalidPassword() {
        loginPage.login(validUsername, invalidPassword);
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match any user in this service"), 
                         "Expected error message not found");
    }

    /**
     * Test Case 10: Verify login fails with locked out user
     * Description: Locked out user should see error message
     */
    @Test(description = "Verify login fails for locked out user", priority = 10)
    public void testLoginWithLockedOutUser() {
        loginPage.login(lockedOutUser, validPassword);
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"), 
                         "Expected error message not found. Actual: " + loginPage.getErrorMessage());
    }

    /**
     * Test Case 11: Verify login page is still displayed after failed login
     * Description: After failed login, user should remain on login page
     */
    @Test(description = "Verify login page is displayed after failed login", priority = 11)
    public void testLoginPageDisplayedAfterFailedLogin() {
        loginPage.login(invalidUsername, invalidPassword);
        
        Assert.assertTrue(loginPage.isLoginPageLoaded(), 
                         "Login page is not displayed after failed login");
        Assert.assertTrue(loginPage.isLoginPageURL(), 
                         "URL is not on login page");
    }

    /**
     * Test Case 12: Verify error message is displayed for empty username with special characters in password
     * Description: Error should be shown for empty username even with password containing special characters
     */
    @Test(description = "Verify error with empty username and special password", priority = 12)
    public void testLoginWithEmptyUsernameSpecialPassword() {
        loginPage.enterPassword("!@#$%^&*()");
        loginPage.clickLoginButton();
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), 
                         "Expected error message not found");
    }

    /**
     * Test Case 13: Verify username field accepts valid input
     * Description: Username field should accept and retain valid username
     */
    @Test(description = "Verify username field accepts valid input", priority = 13)
    public void testUsernameFieldAcceptsInput() {
        loginPage.enterUsername(validUsername);
        
        String enteredUsername = loginPage.getUsernameValue();
        Assert.assertEquals(enteredUsername, validUsername, 
                           "Username field did not accept the input");
    }

    /**
     * Test Case 14: Verify password field accepts valid input
     * Description: Password field should accept and retain valid password
     */
    @Test(description = "Verify password field accepts valid input", priority = 14)
    public void testPasswordFieldAcceptsInput() {
        loginPage.enterPassword(validPassword);
        
        String enteredPassword = loginPage.getPasswordValue();
        Assert.assertEquals(enteredPassword, validPassword, 
                           "Password field did not accept the input");
    }

    /**
     * Test Case 16: Verify login page title is correct
     * Description: Login page title should be 'Swag Labs'
     */
    @Test(description = "Verify login page title is correct", priority = 16)
    public void testLoginPageTitle() {
        String pageTitle = loginPage.getLoginPageTitle();
        Assert.assertTrue(pageTitle.contains("Swag Labs"), 
                         "Login page title is not correct. Actual: " + pageTitle);
    }

    /**
     * Test Case 17: Verify login page URL is correct
     * Description: Login page should be on saucedemo.com domain
     */
    @Test(description = "Verify login page URL is correct", priority = 17)
    public void testLoginPageURL() {
        Assert.assertTrue(loginPage.isLoginPageURL(), 
                         "Login page URL is not correct");
    }

    /**
     * Test Case 18: Verify case sensitivity in username field
     * Description: Username should be case sensitive
     */
    @Test(description = "Verify case sensitivity in username", priority = 18)
    public void testLoginWithDifferentCaseUsername() {
        loginPage.login("STANDARD_USER", validPassword);
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed for different case username");
    }
}
