# Selenium TestNG Automation Framework

Project: Selenium TestNG Automation Framework

This README is written in simple language. It explains what the project contains and how to run tests and view reports.

---

## Project structure

```
Selenium-TestNg-Automation-Framework/
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── tests/         # Test classes (extend BaseTest)
│   │   │   ├── pages/         # Page Object Model classes
│   │   │   └── utils/         # Utility classes (report, listeners, helpers)
│   │   └── resources/         # config.properties, test data
│   └── main/
├── pom.xml                    # Maven dependencies and build
├── testng.xml                 # TestNG suite and listeners
├── README.md
└── target/                    # Build and test output
```

---

## Folder descriptions (plain language)

- `src/test/java/tests/` : Put your test files here. Each test should extend `BaseTest` so browser setup and teardown happen automatically.
- `src/test/java/pages/` : Put page classes here. A page class contains locators and actions for a page (keeps tests clean).
- `src/test/java/utils/` : Helpers and utilities live here (report setup, listener that takes screenshots, config reader, etc.).
- `src/test/resources/` : Configuration files like `config.properties` and test data files.
- `target/` : Generated files after running tests (reports, compiled classes).

---

## Prerequisites (what you need installed)

- Java 11 or newer
- Maven (run `mvn -v` to check)
- Chrome or Firefox browser installed
- Optional: an IDE (VS Code, IntelliJ, Eclipse)

---

## Dependencies (what the project uses)

These are in `pom.xml` (Maven will download them):
- Selenium WebDriver
- TestNG
- WebDriverManager (auto-downloads browser drivers)
- ExtentReports (for HTML reports)
- Log4j (logging)

---

## How to run tests (easy steps)

1. Open a terminal in the project root folder.
2. To run all tests:

```bash
mvn test
```

3. To run a single test class (example):

```bash
mvn -Dtest=SampleTest test
```

4. To run tests defined in `testng.xml`:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## Configuration (what to change)

Open `src/test/resources/config.properties` and edit values like:
- `browser` = chrome or firefox
- `app.url` = the website URL your tests should open
- `implicit.wait`, `explicit.wait` = wait times in seconds
- `report.path` = folder where HTML reports are written (default: `test-reports/`)

Example:
```
browser=chrome
app.url=https://www.google.com
report.path=test-reports/
```

---

## Reports and screenshots (how it works)

- After tests run, an HTML report is created in the folder set by `report.path` (default `test-reports/`).
- If a test fails, a screenshot is saved to `<report-path>/screenshots/` and the image is embedded in the HTML report.
- To view the report: open the generated `ExtentReport_*.html` file in your browser.

Quick check after a failed test:
```bash
# list screenshots
ls test-reports/screenshots
# open report (on Windows you can double-click the HTML file)
```

Notes:
- The screenshot code expects your tests to have a `protected WebDriver driver;` field (this is in `BaseTest`). If your tests use a different driver variable name, update the listener or add a `getDriver()` method in your BaseTest.

---

## Troubleshooting (common problems and fixes)

- Tests fail to start: check Java and Maven versions.
- Browser driver errors: WebDriverManager usually fixes drivers automatically. Ensure an internet connection the first time.
- Screenshot not found in report:
  1. After a failure, check `test-reports/screenshots` for PNG files.
  2. If files exist but not visible, open the HTML report file directly with your browser (file://).
  3. If no PNGs are saved, make sure tests extend `BaseTest` and `driver` is a field.

---

## Best practices (short list)

- Keep tests small and focused.
- Use page classes to hide selectors and actions.
- Use explicit waits for elements.
- Add assertions to verify expected behavior.
- Keep configuration in `config.properties`.

---