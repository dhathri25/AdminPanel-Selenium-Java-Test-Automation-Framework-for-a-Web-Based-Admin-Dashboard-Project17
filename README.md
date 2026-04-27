# Selenium Java Automation Framework – Admin Panel Testing

## Overview

This project is a Selenium-based automation testing framework developed using Java and TestNG. It is designed to automate end-to-end testing of a demo admin dashboard application and follows the Page Object Model (POM) design pattern to ensure maintainability, scalability, and reusability.

The framework includes support for data-driven testing, configuration management, reporting, and automated screenshot capture on test failures.

## Application Under Test

URL: https://testpages.eviltester.com/styled/index.html

This application provides multiple components such as forms, tables, alerts, dynamic elements, and frames for automation practice.

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* ExtentReports

## Framework Features

* Page Object Model (POM) design pattern
* Data-driven testing using JSON
* TestNG annotations, DataProviders, and test configuration
* Centralized configuration using config.properties
* Screenshot capture on test failure using TestNG listeners
* ExtentReports integration for HTML reporting
* Reusable and modular framework design

## Project Structure
```
AdminPanel/
│
├── src/test/java/com/srm/framework/
│   ├── core/
│   │   └── BaseTest.java
│   │
│   ├── pages/
│   │   ├── BasePage.java
│   │   ├── FormPage.java
│   │   ├── TablePage.java
│   │   ├── AlertPage.java
│   │   ├── DynamicPage.java
│   │   └── FramePage.java
│   │
│   ├── tests/
│   │   ├── FormTests.java
│   │   ├── TableTests.java
│   │   ├── AlertTests.java
│   │   ├── DynamicTests.java
│   │   └── FrameTests.java
│   │
│   ├── utils/
│   │   ├── ConfigReader.java
│   │   ├── JsonDataReader.java
│   │   ├── ScreenshotUtil.java
│   │   └── ExtentReportManager.java
│   │
│   └── listeners/
│       └── TestListener.java
│
├── src/test/resources/
│   ├── config.properties
│   └── testdata/
│       └── form-data.json
│
├── screenshots/
├── reports/
├── testng.xml
├── pom.xml
└── README.md
```
## Test Modules Covered

### 1. Form Interactions

* Submit basic HTML form with valid data
* Validate dropdown selections
* Validate radio button and checkbox selections
* Handle empty form submission
* Validate boundary values for input fields

### 2. Table Operations

* Verify table headers
* Perform sorting on columns
* Validate sorted data changes
* Handle pagination and verify row updates

### 3. Alerts and Dialogs

* Handle JavaScript alert and verify result
* Handle confirm dialog (accept and dismiss)
* Handle prompt dialog and validate entered input

### 4. Dynamic Elements

* Handle hidden and dynamically loaded elements
* Verify element visibility after trigger actions
* Validate redirect functionality

### 5. Frames

* Switch to iframe and interact with elements
* Handle nested iframes
* Switch back to default content and verify page behavior

## Page Object Model (POM)

* Each page is represented by a dedicated Page class
* Locators and actions are encapsulated within Page classes
* Test classes only interact with Page methods
* Shared utilities are implemented in BasePage

## Configuration Management

All configuration values are stored in:
src/test/resources/config.properties

Example:  
browser=chrome  
baseUrl=https://testpages.eviltester.com/styled/index.html  
timeout=10

## Data-Driven Testing

* Test data is stored in JSON format
* DataProvider reads data using JsonDataReader
* Supports multiple test scenarios using a single test method

## WebDriver Management

* WebDriverManager is used to automatically manage browser drivers
* Browser type is controlled via config.properties

## Wait Strategy

* Explicit waits using WebDriverWait and ExpectedConditions
* No use of Thread.sleep()

## Screenshot on Failure

* Implemented using TestNG ITestListener
* Screenshots captured automatically on test failure
* Stored in the screenshots/ directory with timestamp

## Test Reporting

* ExtentReports generates HTML reports after execution
* Reports include:

  * Test names
  * Execution status (pass/fail)
  * Failure screenshots

## Running the Tests

### Using Maven

mvn test

### Using TestNG

Run the testng.xml file from your IDE

## Key Design Principles

* Clean separation between test, page, and utility layers
* No hardcoded values in test classes
* Reusable and maintainable code structure
* Consistent naming conventions
* Scalable framework design

## Author

Dhathri Putty

