package com.salesforce.tests;

import com.salesforce.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InvalidLoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
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
    public void testInvalidLoginDisplaysErrorMessage() {
        try {
            Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be visible");
            Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be visible");
            Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible");

            String invalidUser = "invalid.user@nonexistentdomain.com";
            String invalidPass = "WrongPassword123#";

            loginPage.doLogin(invalidUser, invalidPass);

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message container should be visible upon invalid login");

            String actualErrorText = loginPage.getErrorMessage();
            Assert.assertTrue(actualErrorText.contains("Please check your username and password"),
                    "Actual error message does not match expected text. Found: " + actualErrorText);
        } catch (Exception e) {
            Assert.fail("Invalid login test execution failed with exception: " + e.getMessage());
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
