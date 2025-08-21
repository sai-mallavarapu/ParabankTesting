package com;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pages.LoginPage;
import pages.RegistrationPage;

@Listeners(com.MyListener1.class)
public class Parabank {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Parabank.class);

    public WebDriver getDriver() {
        return driver;
    }

    @Parameters({ "browserName", "url" })
    @BeforeClass(alwaysRun = true)
    public void LaunchBrowser(String browserName, String url) {
        logger.info("Launching browser: " + browserName);

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.error("Invalid browser name provided!");
                throw new IllegalArgumentException("Invalid Browser: " + browserName);
        }
        driver.manage().window().maximize();
        driver.get(url);
        logger.info("Navigated to URL: " + url);
    }

    // 🔹 Parallel execution + invocation count
    @Test(priority = 1, dataProvider = "registerData", threadPoolSize = 2, invocationCount = 1)
    public void RegisterNewUser(String firstname, String lastname, String streetname, String city, String state,
                                String zipcode, String phonenumber, String ssn, String username, String password, String repeatedpassword) {

        logger.info("Starting registration test with username: " + username);

        RegistrationPage register = new RegistrationPage(driver);
        driver.get("https://parabank.parasoft.com/");

        register.clickRegisterLink();
        register.setFirstName(firstname);
        register.setLastName(lastname);
        register.setAddress(streetname);
        register.setCity(city);
        register.setState(state);
        register.setZipCode(zipcode);
        register.setPhone(phonenumber);
        register.setSSN(ssn);
        register.setUsername(username);
        register.setPassword(password);
        register.setConfirmPassword(repeatedpassword);
        register.clickRegisterButton();

        logger.info("User registered successfully: " + username);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))).click();
        logger.info("Logged out after registration.");
    }

    @Test(priority = 2, dataProvider = "loginData", threadPoolSize = 2, invocationCount = 1)
    public void Login(String userName, String password) {
        logger.info("Starting login test with username: " + userName);

        LoginPage login = new LoginPage(driver);
        driver.get("https://parabank.parasoft.com/");

        login.setUsername(userName);
        login.setPassword(password);
        login.clickLogin();

        logger.info("User logged in successfully: " + userName);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))).click();
        logger.info("Logged out after login.");
    }

    @AfterClass
    public void afterClass() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
            logger.info("Browser closed successfully.");
        }
    }

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() throws IOException {
        logger.info("Fetching registration data from Excel...");
        return ExcelDataUtils.getSheetData("src/test/resources/RegistrationParabank.xlsx", "Sheet1");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        logger.info("Fetching login data from Excel...");
        return ExcelDataUtils.getSheetData("src/test/resources/RegistrationParabank.xlsx", "Sheet2");
    }
}
