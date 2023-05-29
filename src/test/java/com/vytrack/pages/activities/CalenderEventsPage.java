package com.vytrack.pages.activities;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalenderEventsPage {
    static WebDriver driver = Driver.getDriver();

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEventBtn;

    @FindBy(css = "id^=oro_calendar_event_form_title']")
    public WebElement titleInputLocator;

    @FindBy(css = "[id^date_selector_oro_calendar_event_form_stat']")
    public WebElement startDateLocator;

    @FindBy(css = "[id^date_selector_oro_calendar_event_form_end']")
    public WebElement endDateLocator;

    @FindBy(css = "a[title='Grid Settings']")
    public WebElement gridSettingsElement;

    @FindBy(css = "a[title='Reset']")
    public WebElement resetBtnElement;

    @FindBy(className = "grid-header-cell__label") //we don't need dot! .grid-header-cell__label
    public List<WebElement> headers;


    public CalenderEventsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void selectGridSetting(String name, boolean yesOrNo) {
        //Click on grid options
        gridSettingsElement.click();
        //Create locator for grid option based on the name
        String locator = "//td//label[text()='" + name + "']/../following-sibling::td/input";
        //find element
        WebElement gridOption = driver.findElement(By.xpath(locator));
        //if param yesOrNo is true, and checkbox is not selected yet
        //click on it OR
        //Checkbox is selected, and you want to unselect it
        if (yesOrNo && !gridOption.isSelected() ||
                !yesOrNo && gridOption.isSelected()) {
            gridOption.click();
        }
    }

    /*
    let's write a method
    that will take a headerName as a parameter and will try to look up for header name in the collection of header
    return false otherwise return true
     */
    public boolean verifyHeaderExist(String headerNameOrColumnName) {
        for (WebElement tableHeader : headers) {
//            System.out.println(tableHeader.getText());
            if (tableHeader.getText().equals(headerNameOrColumnName)) {
                return true;
            }
        }
        return false;
    }
}
