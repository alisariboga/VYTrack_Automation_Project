package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalenderEventsPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.annotations.Test;

public class CalendarEventsTests extends TestBase {

    @Test
    public void verifyTitleColumn() {
        LoginPage loginPage = new LoginPage();
        CalenderEventsPage calendarPage = new CalenderEventsPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //unselect title option from grid settings
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarPage.selectGridSetting("Title", false);
        SeleniumUtils.waitPlease(3);
    }
}
