# AutomationExercise Login Automation

A Selenium WebDriver test automation project that verifies the login flow of [automationexercise.com](https://www.automationexercise.com/). It is built with **Java**, **Maven**, **TestNG** and the **Page Object Model (POM)** design pattern.

## Test Scenario

1. Launch the website
2. Navigate to the Login page (Signup / Login)
3. Enter the registered email address and password
4. Submit the login form
5. Verify the login was successful (the "Logged in as `<name>`" label and the Logout link are displayed)

## Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 17+ | Programming language |
| Maven | 3.8+ | Build and dependency management |
| Selenium WebDriver | 4.25.0 | Browser automation |
| TestNG | 7.10.2 | Test framework and assertions |
| Selenium Manager | built into Selenium | Downloads the browser driver automatically |

## Project Structure

```
automationexercise-selenium-framework/
├── pom.xml
├── testng.xml                          # TestNG suite definition
├── config.local.properties.example     # Template for your private credentials
├── .gitignore
└── src
    ├── main
    │   ├── java/com/automationexercise
    │   │   ├── base
    │   │   │   ├── BasePage.java       # Common waits and actions for all pages
    │   │   │   └── DriverFactory.java  # Creates Chrome / Firefox / Edge drivers
    │   │   ├── pages
    │   │   │   ├── HomePage.java       # Home page Page Object
    │   │   │   └── LoginPage.java      # Login page Page Object
    │   │   └── utils
    │   │       └── ConfigReader.java   # Reads config from multiple sources
    │   └── resources
    │       └── config.properties       # Non-secret defaults (committed)
    └── test/java/com/automationexercise/tests
        ├── BaseTest.java               # Browser setup and teardown
        └── LoginTest.java              # The login test
```

## Prerequisites

- **JDK 17 or higher** (check with `java -version`)
- **Maven 3.8 or higher** (check with `mvn -version`). IntelliJ IDEA includes its own Maven, so a separate install is optional if you only use the IDE.
- **Google Chrome** (default), or Firefox / Edge
- **A registered account on automationexercise.com.** Open the site, click **Signup / Login**, and create an account. Remember the **email**, **password** and **name** you used.

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/<repository-name>.git
cd <repository-name>
```

### 2. Add your credentials

**No credentials are stored in this repository.** Your login details are kept in a local file that Git ignores, so they never get pushed to GitHub. You must provide your own, using **one** of the options below.

The project needs three values:

| Key | Description | Example |
|-----|-------------|---------|
| `login.email` | The email you registered with | `you@example.com` |
| `login.password` | The password you registered with | `your-password` |
| `login.name` | The **Name** entered at sign-up. It appears after "Logged in as" once you log in, and must match exactly (case-sensitive). | `John` |

#### Option A: Local file (recommended)

Copy the template file in the project root (the folder that contains `pom.xml`):

```bash
# macOS / Linux / Git Bash
cp config.local.properties.example config.local.properties

# Windows Command Prompt
copy config.local.properties.example config.local.properties
```

Open the new `config.local.properties` and replace the placeholder values:

```properties
login.email=you@example.com
login.password=your-password
login.name=John
```

`config.local.properties` is listed in `.gitignore`, so it will not be committed.

#### Option B: Environment variables

```bash
# macOS / Linux
export LOGIN_EMAIL="you@example.com"
export LOGIN_PASSWORD="your-password"
export LOGIN_NAME="John"

# Windows PowerShell
$env:LOGIN_EMAIL="you@example.com"
$env:LOGIN_PASSWORD="your-password"
$env:LOGIN_NAME="John"
```

In IntelliJ IDEA you can set these under **Run > Edit Configurations > Environment variables**. This option also suits CI pipelines such as GitHub Actions secrets.

#### Option C: Command-line arguments

```bash
mvn clean test -Dlogin.email=you@example.com -Dlogin.password=your-password -Dlogin.name=John
```

#### Configuration priority

If a value is defined in more than one place, the first match in this list wins:

1. Command-line argument (`-Dlogin.email=...`)
2. Environment variable (`LOGIN_EMAIL`)
3. `config.local.properties`
4. `src/main/resources/config.properties` (blank defaults)

### 3. Run the tests

#### From the terminal

```bash
mvn clean test
```

Optional flags:

```bash
mvn clean test -Dbrowser=firefox     # chrome (default), firefox, edge
mvn clean test -Dheadless=true       # run without opening a browser window
```



## Configuration Reference

Non-secret settings live in `src/main/resources/config.properties`:

| Key | Default | Description |
|-----|---------|-------------|
| `base.url` | `https://www.automationexercise.com/` | Application URL |
| `browser` | `chrome` | `chrome`, `firefox` or `edge` |
| `headless` | `false` | Run without a visible browser window |
| `explicit.wait.seconds` | `15` | Timeout for explicit waits |

## Troubleshooting

| Problem | Cause and fix |
|---------|---------------|
| `Missing config value 'login.email'...` | No credentials were found. Create `config.local.properties` (see step 2). |
| `Logged in as` label not displayed | The email or password is wrong, or the account does not exist. Try logging in manually on the site first. |
| `Logged-in username does not match the registered name` | `login.name` does not match the name shown after "Logged in as" on the site. Copy it exactly. |
| Browser does not start | Make sure the browser is installed and up to date. Selenium Manager needs internet access to download the driver on the first run. |
| Ads block a click | The framework retries with a JavaScript click. If the problem persists, re-run the test. |
| Tests not found by Maven | Run the command from the folder that contains `pom.xml`. |

