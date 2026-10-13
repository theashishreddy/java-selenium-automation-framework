package com.ashish.qa.bdd;

import com.ashish.qa.config.DriverFactory;
import com.ashish.qa.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginStepDefinitions {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("the demo login page is open")
    public void openLoginPage() {
        driver = DriverFactory.createDriver();
        loginPage = new LoginPage(driver).open();
    }

    @When("I sign in with username {string} and password {string}")
    public void signIn(String username, String password) {
        loginPage.loginAs(username, password);
    }

    @Then("I should see the message {string}")
    public void verifyMessage(String expected) {
        Assert.assertEquals(loginPage.messageText(), expected);
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
