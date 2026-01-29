package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * ProductsPage class - Page Object Model for Products Page
 * Displayed after successful login
 */
public class ProductsPage extends BasePage {
    
    // Locators
    private By productTitle = By.cssSelector(".title");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By productList = By.className("inventory_item");
    private By cartBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify products page is loaded
     */
    public boolean isProductsPageLoaded() {
        return utils.isElementDisplayed(productTitle);
    }

    /**
     * Verify products page title
     */
    public String getProductPageTitle() {
        return getPageTitle();
    }

    /**
     * Verify products are displayed
     */
    public boolean areProductsDisplayed() {
        return utils.isElementDisplayed(productList);
    }

    /**
     * Get products page URL
     */
    public String getProductsPageURL() {
        return getCurrentURL();
    }

    /**
     * Verify user is on inventory page after successful login
     */
    public boolean isUserLoggedInSuccessfully() {
        return isProductsPageLoaded() && getProductsPageURL().contains("inventory");
    }
}
