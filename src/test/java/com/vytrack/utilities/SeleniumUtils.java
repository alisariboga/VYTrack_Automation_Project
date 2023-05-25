package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SeleniumUtils {
    public static void verifyEquals(String expectedResult, String actualResult) {
        /*
         *
         *
         * @param browser name
         * @return browser object, otherwise throw exception to prevent test run
         * Verifies if two strings are equals
         * */
        if (expectedResult.equals(actualResult)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed" + "Expected Result: " + expectedResult + "Actual Result: " + actualResult);
        }


    }

    /**
     * This method will put on pause execution
     *
     * @param seconds
     */
    public static void waitPlease(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param page
     * @param driver this method will open example page based on link name
     */
    public static void openPage(String page, WebDriver driver) {
        //we will find all examples on the home page
        List<WebElement> listOfExamples = driver.findElements(By.tagName("a"));
        for (WebElement example : listOfExamples) {
            if (example.getText().contains(page)) {
                example.click();
                break;
            }
        }
    }

    public static void verifyIsDisplay(WebElement element) {
        if (element.isDisplayed()) {
            System.out.println("PASSED " + element.getText() + " is visible");
        } else {
            System.out.println("FAILED " + element.getText() + " is not visible!");
        }
    }

    /**
     * This method will recover in case of exception after unsuccessful the click,
     * and will try to click on element again
     *
     * @param driver
     * @param by
     * @param attempts
     */
    public static void clickWithWait(WebDriver driver, By by, int attempts) {
        int counter = 0;
        //click on element as many as you specified in attempts parameter
        while (counter < attempts) {
            try {
                //selenium must look for element again
                driver.findElement(by).click();
                //if click is successful - then break
                break;
            } catch (WebDriverException e) {
                //if click failed
                //print exception
                System.out.println(e);
                //print attempt
                System.out.println("Attempt :: " + ++counter);
                //wait for 1 second, and try to click again
                waitPlease(1);
            }
        }
    }
}
