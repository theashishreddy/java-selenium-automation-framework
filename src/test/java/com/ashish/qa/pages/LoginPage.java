package com.ashish.qa.pages;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By message = By.id("message");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public LoginPage open() {
        Path html = Paths.get("src", "test", "resources", "web", "login.html").toAbsolutePath();
        driver.get(html.toUri().toString());
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        return this;
    }

    public LoginPage loginAs(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
        return this;
    }

    public String messageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }

    public String resultType() {
        return driver.findElement(message).getAttribute("data-result");
    }
}
