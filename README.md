# TestNG Selenium Automation Framework

A complete automation testing framework using TestNG and Selenium WebDriver with Page Object Model design pattern.

## Project Structure

```
TestNG_Selenium_Proj/
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── tests/              # Test classes
│   │   │   ├── pages/              # Page Object Model classes
│   │   │   └── utils/              # Utility classes
│   │   └── resources/              # Configuration files and test data
│   └── main/
│       └── java/
│           └── com/qa/utils/       # Reusable utility classes
├── pom.xml                         # Maven configuration
├── testng.xml                      # TestNG configuration
└── README.md                       # This file
```

## Folder Description

### src/test/java/tests
Contains all test classes. Each test class extends `BaseTest` to inherit setup and teardown methods.

### src/test/java/pages
Contains Page Object classes following Page Object Model pattern. Each page class extends `BasePage`.

### src/test/java/utils
Contains utility classes:
- **BaseTest**: Base class with setUp and tearDown methods
- **WebDriverUtils**: Common Selenium WebDriver methods for interaction

### src/test/resources
Contains:
- **config.properties**: Configuration settings (browser, URLs, waits)
- **log4j2.xml**: Logging configuration

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox browser
- IDE (Eclipse, IntelliJ IDEA, or VS Code)

## Dependencies

The project includes:
- Selenium WebDriver 4.15.0
- TestNG 7.8.1
- WebDriverManager 5.6.3
- Log4j 2.20.0
- Apache Commons IO
- Gson

## How to Run Tests

### Using Maven
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=SampleTest

# Run with specific browser
mvn test -Dbrowser=firefox
```

### Using TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Using IDE
- Right-click on test class or testng.xml
- Select "Run As" > "TestNG Test"

## Creating New Test Cases

1. Create a new test class in `src/test/java/tests/`
2. Extend `BaseTest` class
3. Use page objects for element interactions
4. Write test methods with `@Test` annotation

Example:
```java
public class LoginTest extends BaseTest {
    
    @Test
    public void testValidLogin() {
        navigateToURL("https://app.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("user@example.com", "password123");
        Assert.assertTrue(loginPage.isLoginSuccessful());
    }
}
```

## Creating New Page Objects

1. Create a new class in `src/test/java/pages/`
2. Extend `BasePage` class
3. Define locators using `By` class
4. Create methods for page actions

Example:
```java
public class LoginPage extends BasePage {
    
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void login(String email, String password) {
        utils.sendKeys(emailField, email);
        utils.sendKeys(passwordField, password);
        utils.click(loginButton);
    }
}
```

## Configuration

Edit `src/test/resources/config.properties` to configure:
- Browser type (chrome/firefox)
- Application URL
- Wait times
- Log level

## Logging

Logs are written to:
- Console output
- `logs/test-logs.log` file

## Best Practices

1. Use Page Object Model pattern for maintainability
2. Use explicit waits instead of implicit waits
3. Use descriptive test names
4. Use assertions to validate test results
5. Keep page locators in page classes
6. Use utility methods for common actions
7. Add test descriptions with `@Test(description="")`
8. Handle exceptions gracefully

## Troubleshooting

### WebDriver not found
- Ensure WebDriverManager dependency is added
- Clear Maven cache: `mvn clean install`

### Tests fail to run
- Check Java version: `java -version`
- Verify Maven installation: `mvn -version`
- Rebuild project: `mvn clean build`

### Timeouts
- Increase wait times in config.properties
- Check if elements are present in DOM
- Verify element locators are correct

## Contact & Support

For issues or questions, please refer to:
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [WebDriverManager GitHub](https://github.com/bonigarcia/webdrivermanager)
