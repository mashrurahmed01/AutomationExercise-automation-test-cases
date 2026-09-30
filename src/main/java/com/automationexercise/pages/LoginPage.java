package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By loginHeading = By.xpath("//h2[normalize-space()='Login to your account']");
    private final By emailInput = By.cssSelector("input[data-qa='login-email']");
    private final By passwordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");
    private final By errorMessage = By.xpath("//p[contains(text(),'incorrect')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginHeadingVisible() {
        return isDisplayed(loginHeading);
    }

    public LoginPage enterEmail(String email) {
        type(emailInput, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public HomePage submit() {
        click(loginButton);
        return new HomePage(driver);
    }

    public HomePage login(String email, String password) {
        return enterEmail(email).enterPassword(password).submit();
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }
}
