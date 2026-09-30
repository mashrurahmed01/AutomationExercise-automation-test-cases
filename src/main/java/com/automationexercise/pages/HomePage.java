package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By signupLoginLink = By.cssSelector("a[href='/login']");
    private final By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By loggedInUserName = By.xpath("//a[contains(text(),'Logged in as')]/b");
    private final By logoutLink = By.cssSelector("a[href='/logout']");
    private final By slider = By.id("slider");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open(String url) {
        driver.get(url);
        return this;
    }

    public boolean isHomePageVisible() {
        return isDisplayed(slider);
    }

    public LoginPage goToLoginPage() {
        click(signupLoginLink);
        return new LoginPage(driver);
    }

    public boolean isLoggedInAsDisplayed() {
        return isDisplayed(loggedInAs);
    }

    public String getLoggedInUserName() {
        return getText(loggedInUserName);
    }

    public boolean isLogoutLinkDisplayed() {
        return isDisplayed(logoutLink);
    }
}
