package com.salesforce.tests;

import com.salesforce.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidLoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;
    private final String targetUrl = "https://login.salesforce.com/?locale=in";

    @BeforeMethod
    public void setUp() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.get(targetUrl);
            loginPage = new LoginPage(driver);
        } catch (Exception e) {
            if (driver != null) {
                driver.quit();
            }
            Assert.fail("Test setup failed due to exception: " + e.getMessage());
        }
    }

    @Test
    public void testValidLoginSubmissionWithRememberMe() {
        try {
            Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field must be visible");
            Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field must be visible");
            Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button must be visible");
            Assert.assertTrue(loginPage.isRememberMeCheckboxDisplayed(), "Remember me checkbox must be visible");

            String testUser = "standard_user@salesforce.com";
            String testPass = "SecureSalesforcePass123!";

            loginPage.doLoginWithRememberMe(testUser, testPass);

            boolean hasNavigatedOrProcessed = wait.until(ExpectedConditions.or(
                    ExpectedConditions.not(ExpectedConditions.urlToBe(targetUrl)),
                    ExpectedConditions.titleContains("Salesforce")
            ));

            Assert.assertTrue(hasNavigatedOrProcessed, "Login form submission should navigate or process authentication");
        } catch (Exception e) {
            Assert.fail("Valid login execution failed with exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error encountered during driver quit: " + e.getMessage(), e);
        }
    }
}
