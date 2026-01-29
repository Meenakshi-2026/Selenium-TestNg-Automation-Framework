package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * HomePage class - Page Object Model for Home Page
 */
public class HomePage extends BasePage {
    // Locators
    private By homePageHeading = By.xpath("//h1[contains(text(), 'Home')]");
    private By searchBox = By.id("search");
    private By searchButton = By.xpath("//button[@type='submit']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify home page is loaded
     */
    public boolean isHomePageLoaded() {
        return utils.isElementDisplayed(homePageHeading);
    }

    /**
     * Search for item
     */
    public void search(String query) {
        utils.sendKeys(searchBox, query);
        utils.click(searchButton);
    }

    /**
     * Get home page title
     */
    public String getHomePageTitle() {
        return getPageTitle();
    }
}
