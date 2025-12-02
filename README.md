# 🚀 Automation Exercise Web Framework

> **ITI Graduation Project**  
> **Submitted by:** Aya Ali Mohamed

---

## 📖 Overview

This project is a robust **Test Automation Framework** designed for the [Automation Exercise](https://automationexercise.com/) website. It serves as the **Graduation Project** for the **Information Technology Institute (ITI)**, demonstrating advanced test automation practices using industry-standard tools.

The framework adopts a **Hybrid** approach, combining **Web UI Automation** with **Playwright** and **API Automation** with **Rest Assured**, ensuring comprehensive test coverage, reliability, and speed.

---

## ✨ Key Features

-   **Hybrid Automation**: Seamlessly integrates UI (Playwright) and API (Rest Assured) testing.
-   **Page Object Model (POM)**: Enhances code maintainability and readability by separating page elements from test logic.
-   **Singleton Design Pattern**: Ensures efficient resource management for driver instances.
-   **Fluent Interface**: Promotes readable and chainable code methods.
-   **Data-Driven Testing**: Supports external data sources (JSON/Excel) for flexible test scenarios.
-   **Parallel Execution**: Leverages TestNG to run tests concurrently, significantly reducing execution time.
-   **Rich Reporting**: Integrated **Allure Report** for detailed, interactive, and visual test results.
-   **CI/CD Ready**: Built with Maven for easy integration with CI/CD pipelines (Jenkins, GitHub Actions).
-   **Log4j2 Logging**: Comprehensive logging for debugging and tracking execution flow.

---

## �️ Technology Stack

| Category | Tool / Library | Version | Description |
| :--- | :--- | :--- | :--- |
| **Language** | ![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white) | 21 | Core programming language. |
| **Web Automation** | ![Playwright](https://img.shields.io/badge/Playwright-1.49.0-2EAD33?style=flat-square&logo=playwright&logoColor=white) | 1.49.0 | Fast and reliable browser automation. |
| **API Automation** | ![Rest Assured](https://img.shields.io/badge/Rest_Assured-5.5.0-008080?style=flat-square&logo=postman&logoColor=white) | 5.5.0 | Powerful Java DSL for testing REST services. |
| **Test Runner** | ![TestNG](https://img.shields.io/badge/TestNG-7.11.0-FF7F00?style=flat-square&logo=testng&logoColor=white) | 7.11.0 | Testing framework for test management and execution. |
| **Build Tool** | ![Maven](https://img.shields.io/badge/Maven-3.9.x-C71A36?style=flat-square&logo=apachemaven&logoColor=white) | 3.x | Dependency management and build automation. |
| **Reporting** | ![Allure](https://img.shields.io/badge/Allure-2.30.0-FFB13B?style=flat-square&logo=qameta&logoColor=white) | 2.30.0 | Flexible and lightweight multi-language test report tool. |
| **Utilities** | Apache POI, Log4j2, JSON Simple | - | File handling, logging, and data parsing. |

---

## 📂 Project Structure

```plaintext
AutomationExerciseWebPlaywrightVersion
├── src
│   ├── main
│   │   ├── java
│   │   │   └── automationexercises
│   │   │       ├── apis          # API Request specifications (Rest Assured)
│   │   │       ├── pages         # Page Object Classes (Playwright)
│   │   │       └── utils         # Utility classes (Config, JSON, Excel)
│   │   └── resources
│   │       ├── config.properties # Global configuration
│   │       └── log4j2.xml        # Logging configuration
│   └── test
│       ├── java
│       │   └── automationexercises
│       │       └── tests         # Test Classes (UI & API)
│       └── resources
│           └── testdata          # External test data (JSON/Excel)
├── testng.xml                    # Main TestNG Suite
├── testng-smoke.xml              # Smoke Test Suite
├── testng-regression.xml         # Regression Test Suite
├── pom.xml                       # Maven Dependencies
└── README.md                     # Project Documentation
```

---

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed on your machine:

1.  **Java Development Kit (JDK) 21**: [Download Here](https://www.oracle.com/java/technologies/downloads/#java21)
2.  **Maven**: [Download Here](https://maven.apache.org/download.cgi)
3.  **IDE**: IntelliJ IDEA (Recommended) or Eclipse.
4.  **Allure Commandline** (Optional): For viewing reports locally.

### Installation

1.  **Clone the Repository**
    ```bash
    git clone <repository-url>
    ```

2.  **Navigate to Project Directory**
    ```bash
    cd AutomationExerciseWebPlaywrightVersion
    ```

3.  **Install Dependencies**
    ```bash
    mvn clean install -DskipTests
    ```

---

## 🏃‍♂️ Running Tests

You can execute tests using **Maven** or directly from your **IDE**.

### 1. Run All Tests
```bash
mvn clean test
```

### 2. Run Smoke Suite
Executes critical path scenarios (e.g., Registration, Login, Checkout).
```bash
mvn clean test -DsuiteXmlFile=testng-smoke.xml
```

### 3. Run Regression Suite
Executes the full regression test suite.
```bash
mvn clean test -DsuiteXmlFile=testng-regression.xml
```

---

## 📊 Reporting

This project uses **Allure Report** to generate comprehensive test reports.

### Generate and View Report
After test execution, run the following command to serve the report locally:

```bash
mvn allure:serve
```

Or generate a static report in the `target` directory:

```bash
mvn allure:report
```

---

## 👩🏻‍💻 Author

**Aya Ali Mohamed**  
*Software Testing Engineer | ITI Graduate*

> This project was developed with ❤️ as part of the ITI Graduation requirements.
