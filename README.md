# 🚀 Hybrid Automation Framework

An enterprise-grade, highly scalable **Hybrid Automation Framework** designed for both UI and API testing. The architecture is engineered with strict separation of concerns, thread safety for parallel execution, and environment-based continuous integration routing.

---

## 📂 Framework Directory Structure

The repository follows standard Maven layout conventions, separating source logic (`src/main`) from validation runtime scripts (`src/test`):

```text
src
├── main
│   ├── java
│   │   └── com.akshay
│   │       ├── driver         # ThreadLocal thread-safe driver engine allocations
│   │       ├── pages          # Page Object Model (POM) element maps & components
│   │       ├── components     # Global shared UI blocks (Header, Navigation)
│   │       ├── waits          # Explicit wait orchestration synchronization wrappers
│   │       ├── util           # Property readers, parsing utilities, and formatters
│   │       ├── listeners      # TestNG Reporting hooks and Extent engine logs
│   │       ├── constants      # Static globally accessible framework configurations
│   │       └── models         # Data POJOs for API Serialization/Deserialization
│   └── resources
│       ├── config.qa.properties      # Target environment execution routes
│       ├── config.uat.properties     # Staging platform properties
│       └── config.prodqa.properties  # Production verification properties
└── test
    ├── java
    │   └── com.akshay
    │       ├── base           # Lifecycle hook execution engine (BaseTest.java)
    │       ├── ui             # Front-end Browser UI test layers (Smoke/Regression)
    │       └── api            # Back-end REST Assured test layers (Smoke/Regression)
    └── resources
        ├── testdata           # Excel sheets, JSON templates, or data-driven assets
        ├── screenshots        # Failure tracking automatic media attachments
        └── testng.xml         # XML test suite runner configuration