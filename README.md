# Hybrid Automation Framework

Java 17 Maven framework for UI automation with Selenium, API testing with Rest Assured, TestNG execution, Excel-driven test data, Log4j logging, and Extent reports.

## Project Structure

```text
src
|-- main
|   |-- java/com/akshay
|   |   |-- api             # API client, endpoints, models, specs, validators
|   |   |-- base            # WebDriver factory, manager, and page base class
|   |   |-- constants       # Shared config keys
|   |   |-- pages           # Page objects
|   |   |-- reporting       # Extent report/test managers
|   |   `-- utilities       # Config, waits, element actions, Excel, screenshots
|   `-- resources
|       |-- config.qa.properties
|       |-- config.prod.properties
|       `-- log4j2.xml
`-- test
    |-- java/com/akshay
    |   |-- api             # API tests
    |   |-- base            # Test lifecycle
    |   |-- dataProvider    # TestNG data providers
    |   |-- listeners       # TestNG listeners
    |   `-- ui              # UI tests
    `-- resources
        |-- TestData/TestData.xlsx
        `-- testng.xml
```

## Run Tests

Run the default QA suite:

```bash
mvn test
```

Run against a specific environment:

```bash
mvn test -Denv=qa
mvn test -Denv=prod
```

The selected environment loads `config.<env>.properties` from `src/main/resources`.

## Reports And Logs

Runtime output is generated under:

```text
test-output/ExtentReport.html
test-output/screenshots/
logs/framework.log
```

These files are ignored by Git because they are generated on each run.

## Configuration

Common keys:

```properties
browser=chrome
headless=true
timeout.implicit=2
timeout.explicit=15
ui.base.url=https://www.saucedemo.com/
api.base.uri=https://reqres.in
api.key=<api-key>
test.data.path=src/test/resources/TestData/TestData.xlsx
```

Supported browsers are `chrome`, `firefox`, and `edge`.
