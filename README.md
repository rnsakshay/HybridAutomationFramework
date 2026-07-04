# Hybrid Automation Framework (UI + API)

A scalable, robust, and industry-standard Hybrid Test Automation Framework built from scratch utilizing **Java**, **Selenium WebDriver**, and **Rest Assured**. This architecture is integrated with **GitHub Actions** to demonstrate seamless CI/CD pipeline execution and automated reporting.

---

## 🏗️ Framework Architecture & Folder Structure

The project is structured around the principles of **Separation of Concerns** and **Clean Code**. Each folder and package has a dedicated architectural responsibility:

```text
HybridAutomationFramework/
│
├── .github/workflows/
│   └── automation-run.yml       # DevOps Layer: GitHub Actions CI/CD pipeline configuration script
│
├── src/main/java/com/akshay/
│   ├── base/                    # Engine Room: Core initialization, ThreadLocal configuration for parallel execution
│   ├── models/                  # Data Modeling: Plain Old Java Objects (POJO) representing API payloads
│   ├── pages/                   # UI Elements: Page Object Model (POM) repositories (Strictly containing actions, no assertions)
│   └── util/                    # Utility Layer: Configuration file readers, custom loggers, and reporting helpers
│
├── src/main/resources/
│   └── config.properties        # Global Settings: Environment URLs, browser definitions, and headless execution flags
│
├── src/test/java/com/akshay/
│   ├── api/                     # Test Scripts: Rest Assured functional API automation test cases
│   └── ui/                      # Test Scripts: Selenium Web UI end-to-end automation test cases
│
└── src/test/resources/
    ├── screenshots/             # Diagnostics: Automated capture laboratory for UI test failure screens
    └── testdata/                # Data Warehouse: External test data files formatted in JSON, Excel, or CSV