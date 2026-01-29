package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.BaseTest;

/**
 * SampleTest class - Sample test cases
 */
public class SampleTest extends BaseTest {

    @Test(description = "Verify user can navigate to home page")
    public void testNavigateToHomePage() {
        // Navigate to the application
        navigateToURL("https://www.google.com");

        // Verify page title
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"), "Page title does not match");
    }

    @Test(description = "Verify page URL is correct")
    public void testPageURL() {
        // Navigate to the application
        navigateToURL("https://www.google.com");

        // Get current URL
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("google"), "URL does not contain expected text");
    }

    @Test(description = "Verify page is loaded successfully")
    public void testPageLoadSuccess() {
        // Navigate to the application
        navigateToURL("https://www.google.com");

        // Verify page title is not empty
        String title = driver.getTitle();
        Assert.assertFalse(title.isEmpty(), "Page title is empty");
    }
}
