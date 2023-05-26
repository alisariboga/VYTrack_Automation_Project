package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test(priority = 1)
    public void loginTest() {
        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.clickRememberMe();
        loginPage.login(username, password);
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        /* to verify that Dashboard page opened
        Once page name Dashboard displays, means that we are logged in successfully */
        Assert.assertEquals(VYTrackUtils.getPageSubtitle(), "Table");
    }

    @Test(priority = 2)
    public void negativeLoginTest() {
        String wrongUsername = "wrongusername";
        String wrongPassword = "wrongpassword";
        LoginPage loginPage = new LoginPage();
        loginPage.login(wrongUsername, wrongPassword);
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid user name or password.");
    }
}
