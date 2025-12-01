# Automation Exercise Web - Playwright Version

This project is a test automation framework for the [Automation Exercise](https://automationexercise.com/) website, built using **Playwright** with **Java**. It utilizes **TestNG** for test management and **Allure** for reporting.

## 🚀 Technologies Used

-   **Java 21**: Programming language.
-   **Playwright (1.49.0)**: Browser automation tool.
-   **TestNG (7.11.0)**: Testing framework.
-   **Allure (2.30.0)**: Reporting framework.
-   **Maven**: Build automation tool.
-   **Log4j2**: Logging framework.

## 📋 Prerequisites

Before running the tests, ensure you have the following installed:

-   **Java Development Kit (JDK) 21**
-   **Maven**
-   **Allure Commandline** (optional, for viewing reports locally)

## 🛠️ Installation

1.  Clone the repository:
    ```bash
    git clone <repository-url>
    ```
2.  Navigate to the project directory:
    ```bash
    cd AutomationExerciseWebPlaywrightVersion
    ```
3.  Install dependencies:
    ```bash
    mvn clean install -DskipTests
    ```

## 🏃‍♂️ Running Tests

### Using Maven

To run all tests:
```bash
mvn clean test
```

To run a specific suite (e.g., Smoke Tests):
```bash
mvn clean test -DsuiteXmlFile=testng-smoke.xml
```

To run Regression Tests:
```bash
mvn clean test -DsuiteXmlFile=testng-regression.xml
```

### Using TestNG XML Runners

You can also run the tests directly from your IDE (IntelliJ IDEA, Eclipse) by right-clicking on the XML files:

-   `testng.xml`: Runs all tests.
-   `testng-smoke.xml`: Runs critical path scenarios (Register, Login, Products, Checkout).
-   `testng-regression.xml`: Runs the full regression suite.

## 📊 Generating Allure Reports

After running the tests, generate and view the Allure report:

```bash
mvn allure:serve
```

Or generate the report to the `target/site/allure-maven-plugin` directory:

```bash
mvn allure:report
```

## 📁 Project Structure

-   `src/main/java`: Contains Page Objects and API implementations.
-   `src/test/java`: Contains Test Classes.
    -   `automationexercises.tests.ui`: UI Tests.
-   `src/test/resources`: Test data and configuration files.

## 📝 Test Annotations

The project uses Allure annotations for better reporting:
-   `@Epic`: High-level project or module name.
-   `@Feature`: Specific feature being tested.
-   `@Story`: User story or specific scenario.
-   `@Severity`: Criticality of the test case.
-   `@Description`: Detailed explanation of the test.
