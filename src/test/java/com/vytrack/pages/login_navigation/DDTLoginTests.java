package com.vytrack.pages.login_navigation;

import com.vytrack.utilities.ExcelUtil;
import com.vytrack.utilities.Pages;
import com.vytrack.utilities.TestBase;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTLoginTests extends TestBase {
    @Test(dataProvider = "credentials_info")
    public void loginTestWithDataProvider(String execute, String username, String password, String firstname, String lastname, String result) {
        Pages page = new Pages();
        extentLogger = report.createTest("Data Driven Testing with excel");
        if (execute.equalsIgnoreCase("y")) {
            page.loginPage().login(username, password);
            String actualFullName = page.dashboardPage().getUsersFullName();
            String expectedName = firstname + lastname;
            Assert.assertEquals(actualFullName, expectedName, "Name doesn't match");
            page.dashboardPage().logout();
        } else {
            throw new SkipException("Test Ignored");
        }

    }

    @DataProvider(name = "credentials_info")
    public Object[][] credentials() {
        ExcelUtil qa3 = new ExcelUtil("QA1-short");
        return qa3.getDataArray();
    }
}
