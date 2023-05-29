package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalenderEventsPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarEventsTests extends TestBase {

    @Test
    public void verifyTitleColumn() {
        extentLogger = report.createTest("Verify column that column names are adjustable");
        LoginPage loginPage = new LoginPage();
        CalenderEventsPage calenderEventsPage = new CalenderEventsPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //unselect title option from grid settings
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calenderEventsPage.selectGridSetting("Title", false);
        SeleniumUtils.waitPlease(3);

        //Verify that title column name is not visible any more
        Assert.assertFalse(calenderEventsPage.verifyHeaderExist("Title"), "Title column name still visible!");

        //to close grid settings menu
        calenderEventsPage.gridSettingsElement.click();

        calenderEventsPage.selectGridSetting("Title", true);
        SeleniumUtils.waitPlease(3);

        //Verify that title column name is visible again
        Assert.assertTrue(calenderEventsPage.verifyHeaderExist("Title"), "Title column name is not visible!");
    }
}
