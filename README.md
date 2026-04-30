# ShopBot - Selenium Automation Testing Framework

## Project Overview

ShopBot is a **Java-based Selenium automation testing framework** designed to automate testing for an e-commerce shopping application. It uses the **SauceDemo** website as the test environment to validate various shopping workflows including login, product browsing, cart management, and checkout processes.

This framework demonstrates modern automation best practices using the **Page Object Model (POM)** design pattern with TestNG for test organization and Extent Reports for detailed test reporting.

---

## Project Details

- **Project Name:** ShopBot
- **Group ID:** com.srm
- **Artifact ID:** ShopBot
- **Version:** 0.0.1-SNAPSHOT
- **Package Type:** JAR
- **Application Under Test:** [SauceDemo - https://www.saucedemo.com](https://www.saucedemo.com)

---

## Key Features

- **Page Object Model (POM)** - Clean separation of test logic and page elements  
- **TestNG Framework** - Organized test execution with listeners and parallel testing support  
- **Data-Driven Testing** - Excel-based test data using Apache POI  
- **Extent Reports** - Beautiful HTML test reports with test execution details  
- **Screenshot Capture** - Automatic screenshot capture on test failures  
- **Configuration Management** - Externalized configuration using properties file  
- **WebDriver Management** - Automatic browser driver management with WebDriverManager  

---

## Tech Stack & Dependencies

| Component | Version |
|-----------|---------|
| **Java** | 1.8+ (from pom.xml) |
| **Selenium WebDriver** | 4.21.0 |
| **TestNG** | 7.11.0 |
| **WebDriverManager** | 5.8.0 |
| **Apache POI** | 5.2.5 (Excel handling) |
| **Extent Reports** | 5.1.1 |
| **Commons IO** | 2.15.1 |
| **JUnit** | 3.8.1 |
| **Maven Surefire Plugin** | 3.2.5 |

---

## Project Structure

```
ShopBot/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── srm/
│   │               └── ShopBot/
│   │
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   ├── BasePage.java          (Base class for all page objects)
│       │   │   └── BaseTest.java          (Base class for all test classes)
│       │   │
│       │   ├── pages/
│       │   │   ├── CartPage.java          (Cart page object model)
│       │   │   ├── CheckoutPage.java      (Checkout page object model)
│       │   │   ├── LoginPage.java         (Login page object model)
│       │   │   └── ProductPage.java       (Product page object model)
│       │   │
│       │   ├── tests/
│       │   │   ├── CartTest.java          (Cart functionality tests)
│       │   │   ├── CheckoutTest.java      (Checkout flow tests)
│       │   │   ├── LoginTest.java         (Login/Authentication tests)
│       │   │   ├── ProductTest.java       (Product browsing tests)
│       │   │   └── ProblemUserTest.java   (Problem user scenario tests)
│       │   │
│       │   ├── utils/
│       │   │   ├── ConfigReader.java      (Reads config.properties)
│       │   │   ├── ExcelUtil.java         (Reads Excel test data)
│       │   │   ├── ExtentManager.java     (Manages Extent Reports)
│       │   │   ├── Listeners.java         (TestNG event listeners)
│       │   │   └── ScreenshotUtil.java    (Screenshot capture utility)
│       │   │
│       │   └── com/
│       │       └── srm/
│       │           └── ShopBot/
│       │
│       └── resources/
│           ├── config.properties          (Test configuration and credentials)
│           ├── logindata.xlsx             (Excel test data file)
│           └── testng.xml                 (TestNG suite configuration)
│
├── reports/
│   └── ExtentReport.html                  (Test execution reports)
│
├── screenshots/
│   └── [Automated test failure screenshots]
│
├── target/                                (Maven build output)
├── test-output/                          (TestNG output)
├── pom.xml                               (Maven configuration)
├── .project                              (Eclipse project file)
├── .classpath                            (Eclipse classpath)
├── .gitignore                            (Git ignore rules)
└── README.md                             (This file)
```

---

## Test Modules

The framework includes automated tests for **5 main modules**:

### 1. **Login Tests** (`LoginTest.java`)
   - Standard user login validation
   - Invalid credential handling
   - Session management

### 2. **Product Tests** (`ProductTest.java`)
   - Product browsing and navigation
   - Product filtering and sorting
   - Product details verification

### 3. **Cart Tests** (`CartTest.java`)
   - Add/remove items from cart
   - Cart quantity updates
   - Cart persistence

### 4. **Checkout Tests** (`CheckoutTest.java`)
   - Checkout flow validation
   - Shipping information entry
   - Payment processing
   - Order confirmation

### 5. **Problem User Tests** (`ProblemUserTest.java`)
   - Special user scenario handling
   - Error state management
   - Recovery workflows

---

## Configuration

### `config.properties`
This file contains all test configuration and test data:

```properties
# Browser Configuration
browser=chrome
baseUrl=https://www.saucedemo.com
timeout=10

# Login Credentials (Module 2)
username=standard_user
password=secret_sauce

# Checkout Details (Module 4)
firstname=Bhavya
lastname=Sree
zipcode=522503

# Problem User (Module 5)
problemUser=problem_user
```

### Test Data
- **logindata.xlsx** - Contains login test data with username/password combinations

---

## Prerequisites

Before running the tests, ensure you have:

1. **Java Development Kit (JDK)** - Version 1.8 or higher
2. **Maven** - Version 3.6+
3. **Git** (optional) - For version control
4. **Internet Connection** - To download dependencies

---

## Installation & Setup

### Step 1: Clone or Download the Project
```bash
git clone <repository-url>
cd ShopBot
```

### Step 2: Install Dependencies
Maven will automatically download all dependencies from `pom.xml`:
```bash
mvn clean install
```

### Step 3: Verify Installation
Check if all dependencies are resolved:
```bash
mvn dependency:tree
```

---

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Module
```bash
# Run Login Tests only
mvn clean test -Dtest=LoginTest

# Run Product Tests only
mvn clean test -Dtest=ProductTest

# Run Cart Tests only
mvn clean test -Dtest=CartTest

# Run Checkout Tests only
mvn clean test -Dtest=CheckoutTest

# Run Problem User Tests only
mvn clean test -Dtest=ProblemUserTest
```

### Run Tests Using TestNG XML
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run Tests in Parallel
```bash
mvn clean test -Dparallel=methods -DthreadCount=4
```

---

## Test Reports

After test execution, reports are generated in multiple locations:

### 1. **Extent Reports**
- **Location:** `reports/ExtentReport.html`
- **View:** Open in web browser for detailed execution report
- Features: Screenshots, logs, test timeline, charts

### 2. **TestNG Reports**
- **Location:** `test-output/`
- Includes XML and HTML reports

### 3. **Screenshots**
- **Location:** `screenshots/`
- Automatically captured on test failures
- Format: `[TestName]_[Timestamp]_[Date].png`

---

## File Descriptions

### Base Classes

| File | Purpose |
|------|---------|
| `BaseTest.java` | Parent class for all test classes - contains setup, teardown, WebDriver initialization |
| `BasePage.java` | Parent class for all page objects - contains common locators and methods |

### Page Objects (POM)

| File | Purpose |
|------|---------|
| `LoginPage.java` | Locators and methods for login page interactions |
| `ProductPage.java` | Locators and methods for product listing and browsing |
| `CartPage.java` | Locators and methods for shopping cart operations |
| `CheckoutPage.java` | Locators and methods for checkout flow |

### Utility Classes

| File | Purpose |
|------|---------|
| `ConfigReader.java` | Reads properties from config.properties file |
| `ExcelUtil.java` | Reads and writes data to Excel (logindata.xlsx) |
| `ExtentManager.java` | Initializes and manages Extent Reports |
| `ScreenshotUtil.java` | Captures screenshots on failures |
| `Listeners.java` | TestNG listeners for test lifecycle events |

### Test Classes

| File | Purpose |
|------|---------|
| `LoginTest.java` | Tests for authentication and login flows |
| `ProductTest.java` | Tests for product browsing and filtering |
| `CartTest.java` | Tests for cart operations |
| `CheckoutTest.java` | Tests for complete checkout process |
| `ProblemUserTest.java` | Tests for special problem user scenarios |

---

## Common Tasks

### View Extent Reports
1. Navigate to the project directory
2. Open `reports/ExtentReport.html` in a web browser
3. View detailed test results with screenshots

### Update Test Data
1. Edit `config.properties` for simple values
2. Edit `logindata.xlsx` for complex test data scenarios

### Add New Test Cases
1. Create a new test method in the appropriate test class (e.g., `LoginTest.java`)
2. Use page object methods for interactions
3. Use TestNG assertions for validations
4. Run tests using Maven commands

### Debug Tests
1. Add breakpoints in your test code
2. Run tests in debug mode:
   ```bash
   mvn -Dmaven.surefire.debug test
   ```
3. Connect your IDE debugger

---

## Troubleshooting

### Issue: "WebDriver not found"
**Solution:** WebDriverManager automatically downloads the correct driver. Ensure internet connection and Maven runs successfully.

### Issue: "Tests timing out"
**Solution:** Increase timeout value in `config.properties`:
```properties
timeout=20  # Increase from 10 to 20 seconds
```

### Issue: "Excel file not found"
**Solution:** Ensure `logindata.xlsx` exists in `src/test/resources/` directory.

### Issue: "Screenshots not capturing"
**Solution:** Ensure `screenshots/` directory exists. Create it manually if needed.

### Issue: "Port already in use"
**Solution:** Close other instances of the same application or change the port configuration.

---

## Best Practices Used

- **Page Object Model** - Better maintainability and reusability  
- **Data-Driven Testing** - Separate test data from test logic  
- **Configuration Externalization** - Easy environment switching  
- **Proper Wait Handling** - Implicit and explicit waits implemented  
- **Screenshot on Failure** - Better debugging and reporting  
- **Listener Implementation** - Enhanced test reporting and logging  
- **Maven Best Practices** - Proper project structure and dependency management  

---

## Support & Documentation

For more information about the technologies used:

- **Selenium Documentation:** [https://www.selenium.dev/documentation/](https://www.selenium.dev/documentation/)
- **TestNG Documentation:** [https://testng.org/doc/](https://testng.org/doc/)
- **Maven Documentation:** [https://maven.apache.org/guides/](https://maven.apache.org/guides/)
- **Extent Reports:** [https://www.extentreports.com/](https://www.extentreports.com/)

---

## Version History

| Version | Date | Description |
|---------|------|-------------|
| 0.0.1-SNAPSHOT | 2026 | Initial framework setup with 5 test modules |

---

## License

This project is part of the QA Automation suite and is used for learning and automation testing purposes.

---

**Last Updated:** April 2026

For questions or issues, please contact the QA team.
