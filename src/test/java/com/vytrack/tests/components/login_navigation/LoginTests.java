package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    LoginPage loginPage = new LoginPage();

    @Test
    public void loginTest() {
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.clickRememberMe();
        loginPage.login(username, password);
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        /*
        to verify that Dashboard page opened
        Once page name Dashboard displays, means that we are logged successfully
         */
        Assert.assertEquals(VYTrackUtils.getPageSubtitle(), "Table");
    }

    @Test
    public void negativeLoginTest() {
        loginPage.login("wrongusername", "wrongpassword");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid user name or password.");
    }

}
