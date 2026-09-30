package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Login with a registered user and verify success")
    public void verifyUserCanLoginWithValidCredentials() {

        HomePage homePage = new HomePage(driver).open(ConfigReader.get("base.url"));
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page was not loaded");


        LoginPage loginPage = homePage.goToLoginPage();
        Assert.assertTrue(loginPage.isLoginHeadingVisible(), "'Login to your account' heading not visible");


        homePage = loginPage.login(ConfigReader.getRequired("login.email"), ConfigReader.getRequired("login.password"));


        Assert.assertTrue(homePage.isLoggedInAsDisplayed(), "'Logged in as' label not displayed - login failed");
        Assert.assertEquals(homePage.getLoggedInUserName(), ConfigReader.getRequired("login.name"),
                "Logged-in username does not match the registered name");
        Assert.assertTrue(homePage.isLogoutLinkDisplayed(), "Logout link not displayed after login");
    }
}
