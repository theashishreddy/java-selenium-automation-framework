# Java + Selenium Automation Framework (Portfolio Project)

A starter QA automation project built around the skills requested in the Automation Test Engineer job description:
- Java 17, Maven and Selenium WebDriver
- TestNG and Page Object Model (POM)
- Cucumber BDD feature and step definitions
- REST API checks with RestAssured against a deterministic local test API
- SQL/database validation with H2 and JDBC
- Jenkins pipeline example
- Local HTML demo app so the UI test does not depend on a third-party website

> **Important:** This is a portfolio/learning project scaffold, not evidence that you have already used every tool professionally. Run the tests, understand the code, and only add the technologies to your resume once you can explain and demonstrate them.

## Prerequisites
1. JDK 17 or newer (`java -version`)
2. Maven 3.9+ (`mvn -version`)
3. Google Chrome installed (Selenium Manager normally resolves the driver automatically)

## Run
Open a terminal in this folder and run:

```bash
mvn clean test
```

The UI test uses a local HTML file under `src/test/resources/web/`. Selenium runs headlessly by default through the Maven property. To try a visible browser, run:

```bash
mvn clean test -Dheadless=false
```

If Chrome is unavailable on your machine, comment out `UiLoginTest` in the short term and run the API/database tests separately. Do not claim the UI suite passed unless you ran it successfully.

## Framework layout

```text
src/test/java/com/ashish/qa/
├── api/LocalApiServer.java
├── api/ApiTests.java
├── config/DriverFactory.java
├── db/DatabaseValidationTest.java
├── pages/LoginPage.java
├── ui/UiLoginTest.java
└── bdd/
    ├── RunCucumberTest.java
    └── LoginStepDefinitions.java
src/test/resources/
├── features/login.feature
└── web/login.html
Jenkinsfile
```

## What each part demonstrates
- **Page Object Model:** locators and page actions live in `LoginPage`.
- **Reusable driver setup:** `DriverFactory` centralizes Chrome options and driver lifecycle.
- **API testing:** a small local HTTP server exposes `/health` and `/users/1`; RestAssured validates status codes and JSON fields.
- **Database validation:** H2/JDBC creates a sample orders table and checks stored data with SQL.
- **BDD:** `login.feature` describes a user scenario in business-readable Gherkin.
- **CI:** `Jenkinsfile` checks out the repository and runs Maven tests.

## Test cases included
| Area | Scenario | Expected result |
|---|---|---|
| UI | Valid demo login | Success message appears |
| UI | Invalid demo login | Error message appears |
| API | GET `/health` | HTTP 200 and `status=UP` |
| API | GET `/users/1` | HTTP 200 and expected user details |
| Database | Validate order total/status | SQL query returns expected row |
| BDD | Valid login flow | Gherkin scenario passes |

## Before adding to your resume
1. Run `mvn clean test` and resolve any local environment issues.
2. Add a screenshot or test report only after generating it yourself.
3. Push the project to your GitHub account only after reviewing it and understanding each class.
4. Be ready to explain locators, waits, Page Object Model, assertions, HTTP status codes, JSON validation, JDBC, BDD, and how the CI job works.
5. Cucumber, RestAssured and TestNG are included in this project scaffold; describe them as project experience only after you have implemented, run and understood the tests.

## Troubleshooting
- **Java not found:** install JDK 17+ and set `JAVA_HOME`.
- **Maven not found:** install Maven and ensure `mvn` is on PATH.
- **Chrome/driver issue:** update Chrome; Selenium Manager may need internet access on first run.
- **Dependency download issue:** check internet/proxy settings, then retry `mvn clean test`.
- **Port 8089 busy:** stop the other process using port 8089 before running API tests.
