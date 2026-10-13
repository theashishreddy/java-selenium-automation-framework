package com.ashish.qa.ui;

import com.ashish.qa.config.DriverFactory;
import com.ashish.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UiLoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
        loginPage = new LoginPage(driver).open();
    }

    @Test
    public void validCredentialsShouldShowSuccess() {
        loginPage.loginAs("qauser", "Pass123!");
        Assert.assertEquals(loginPage.messageText(), "Login successful");
        Assert.assertEquals(loginPage.resultType(), "success");
    }

    @Test
    public void invalidCredentialsShouldShowError() {
        loginPage.loginAs("wrong-user", "wrong-password");
        Assert.assertEquals(loginPage.messageText(), "Invalid username or password");
        Assert.assertEquals(loginPage.resultType(), "error");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
