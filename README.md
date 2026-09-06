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

Run with browser and headless overrides:

```bash
mvn test -Denv=qa -Dbrowser=chrome -Dheadless=true
```

The selected environment loads `config.<env>.properties` from `src/main/resources`.
System properties override values from the properties file.

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

Configuration values can be overridden by matching system properties or environment variables.

Examples:

```bash
mvn test -Dbrowser=firefox
mvn test -Dapi.key=<api-key>
```

Environment variable names use uppercase with dots replaced by underscores:

```text
BROWSER=chrome
HEADLESS=true
UI_BASE_URL=https://www.saucedemo.com/
REQRES_API_KEY=<api-key>
```

## GitHub Actions

The workflow is defined in:

```text
.github/workflows/automation-run.yml
```

It runs automatically on:

```text
push to main or master
pull requests to main or master
```

It can also be started manually from the GitHub Actions tab with these inputs:

```text
environment: qa or prod
browser: chrome, firefox, or edge
```

The workflow executes:

```bash
mvn test -Denv=${ENVIRONMENT} -Dbrowser=${BROWSER} -Dheadless=true
```

### GitHub Secrets

Add this repository secret if you want CI to provide the ReqRes API key without relying on the checked-in config value:

```text
REQRES_API_KEY
```

GitHub path:

```text
Repository Settings > Secrets and variables > Actions > New repository secret
```

### CI Artifacts

Each workflow run uploads these artifacts:

```text
target/surefire-reports/
test-output/
logs/
```

Use these artifacts to inspect TestNG results, Extent reports, screenshots, and framework logs after a failed run.
