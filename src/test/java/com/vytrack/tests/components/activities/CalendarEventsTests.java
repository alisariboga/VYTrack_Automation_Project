package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalendarEventsPage;
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
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //unselect title option from grid settings
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        SeleniumUtils.waitForStaleElement(calendarEventsPage.gridSettingsElement);
        calendarEventsPage.selectGridSetting("Title", false);

        //Verify that title column name is not visible anymore
        Assert.assertFalse(calendarEventsPage.verifyHeaderExist("Title"), "Title column name still visible!");

        //to close grid settings menu
        calendarEventsPage.gridSettingsElement.click();

        calendarEventsPage.selectGridSetting("Title", true);
        SeleniumUtils.waitPlease(3);

        //Verify that title column name is visible again
        Assert.assertTrue(calendarEventsPage.verifyHeaderExist("Title"), "Title column name is not visible!");
    }
}
