package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VYTrackUtils {
    //we don't want to access these variables outside.
    private static String usernameLocator = "prependedInput";
    private static String passwordLocator = "prependedInput2";
    private static String loaderMaskLocator = "div[class='loader-mask shown']";
    private static String pageTitleLocator = "h1[class='oro-subtitle']";

    /**
     * Login into vytrack application
     *
     * @param driver
     * @param username
     * @param password
     */

    public static void login(WebDriver driver, String username, String password) {
        driver.findElement(By.id(usernameLocator)).sendKeys(username);
        //Keys.ENTER means click enter after entering password
        //in this way, we don't need to click login button
        driver.findElement(By.id(passwordLocator)).sendKeys(password, Keys.ENTER);
        SeleniumUtils.waitPlease(3);
    }

    /**
     * This method will navigate user to the specific module on vytrack application
     * For example: if tab is equals to activities, module equals to Calls,
     * Ten method will navigate user to this page: https://qa2.vytrack.com/call/
     *
     * @param driver
     * @param tab
     * @param module
     */

    public static void navigateToModule(WebDriver driver, String tab, String module) {
        String tabLocator = "//span[contains(text(), '" + tab + "') and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[contains(text(), '" + module + "') and contains(@class, 'title title-level-2')]";
        //driver.findElement(By.xpath(tabLocator)).click();
        SeleniumUtils.clickWithWait(driver, By.xpath(tabLocator), 5);
        SeleniumUtils.waitPlease(1);
        //driver.findElement(By.xpath(moduleLocator)).click();
        SeleniumUtils.clickWithWait(driver, By.xpath(moduleLocator), 5);
        SeleniumUtils.waitPlease(4);
    }

    /**
     * This method will navigate user to the specific module on vytrack application
     * For example: if tab is equals to activities, module equals to Calls,
     * Ten method will navigate user to this page: https://qa2.vytrack.com/call/
     *
     * @param tab
     * @param module
     */

    public static void navigateToModule(String tab, String module) {
        String tabLocator = "//span[contains(text(), '" + tab + "') and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[contains(text(), '" + module + "') and contains(@class, 'title title-level-2')]";
        SeleniumUtils.clickWithWait(Driver.getDriver(), By.xpath(tabLocator), 5);
        Driver.getDriver().findElement(By.xpath(moduleLocator)).click();
    }

    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled but try/catxh block
     * Thus, we can continue in any case
     *
     * @param driver
     */
    public static void waitUntilLoaderScreenDisappear(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigurationReader.getProperty("excplicitwait"))));
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(loaderMaskLocator))));
        } catch (Exception e) {
            System.out.println(e + " :: Loader mask DOES NOT present");
        }
    }

    /**
     * @return page name, for example: Dashboard
     */
    public static String getPageSubtitle() {
        //any time we are verifying page name, or page subtitle, loader mask appears
        waitUntilLoaderScreenDisappear(Driver.getDriver());
        return Driver.getDriver().findElement(By.cssSelector(pageTitleLocator)).getText();
    }
}
