package com.vytrack.pages.activities;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalenderEventsPage {

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEventBtn;

    @FindBy(css = "id^=oro_calendar_event_form_title']")
    public WebElement titleInputLocator;

    @FindBy(css = "[id^date_selector_oro_calendar_event_form_stat']")
    public WebElement startDate;

    @FindBy(css = "[id^date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;
    

    public CalenderEventsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
