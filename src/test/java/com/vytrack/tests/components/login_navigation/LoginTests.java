package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginTest() {
        extentLogger = report.createTest("Login as a store manager");
        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.clickRememberMe();
        loginPage.login(username, password);
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        /* to verify that Dashboard page opened
        Once page name Dashboard displays, means that we are logged in successfully */
        Assert.assertEquals(VYTrackUtils.getPageSubtitle(), "Table");
        extentLogger.pass("Verified that page name is Table");
    }

    @Test
    @Parameters({"username", "password"})
    public void loginTestWithParameters(@Optional String username, @Optional String password) {
        extentLogger = report.createTest("Login as a store manager");
        LoginPage loginPage = new LoginPage();
        loginPage.clickRememberMe();
        System.out.println(username + " " + password);
        loginPage.login(username, password);
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        Assert.assertEquals(VYTrackUtils.getPageSubtitle(), "Table");
        extentLogger.pass("Verified that page name is Table");
    }

    @Test
    public void negativeLoginTest() {
        extentLogger = report.createTest("Logon with invalid credentials");
        String wrongUsername = "wrongusername";
        String wrongPassword = "wrongpassword";
        LoginPage loginPage = new LoginPage();
        extentLogger.info("Logging with username: wrongusername and password: wrongpassword");
        loginPage.login(wrongUsername, wrongPassword);
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid user name or password.");
        extentLogger.pass("Verified that warning message displayed: Invalid user name or password");
    }
}
