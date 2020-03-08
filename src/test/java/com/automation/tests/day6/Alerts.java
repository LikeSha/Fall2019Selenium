package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Alerts {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/javascript_alerts");
        BrowserUtils.wait(3);
        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        buttons.get(0).click();//to click on the first button
        BrowserUtils.wait(3);
        // to get the text from popup message
        String popupText = driver.switchTo().alert().getText();
        System.out.println("popupText = " + popupText);

        driver.switchTo().alert().accept(); // to click OK
        String expected = "You successfully clicked an alert";
        String actual = driver.findElement(By.id("result")).getText();

        if(expected.equals(actual)){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
            System.out.println("Expected : " + expected);
            System.out.println("Actual : " + actual);


            // in this case : TEST FAILED
            //Expected : You successfully clicked an alert
            //Actual : You successfuly clicked an alert

            // because of original text spell "sucessfuly" wrongly ,our test fail means :
            // WE FOUND A BUG !
        }

        System.out.println("-----TEST TWO-----");

        BrowserUtils.wait(3);

        buttons.get(1).click();//to click on the 2nd button
        BrowserUtils.wait(3);
        //to click cancel
        driver.switchTo().alert().dismiss();

        String expected2 = "You clicked: Cancel";
        String actual2 = driver.findElement(By.id("result")).getText();

        if(expected2.equals(actual2)){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
            System.out.println("Expected : " + expected2);
            System.out.println("Actual : " + actual2);
        }
         // TASK : click on button #3
        //Enter some text : Hello,World!
        //VERIFY THAT RESULT TEXT ENDS WITH Hello,World!

        System.out.println("---TEST THREE---");
        BrowserUtils.wait(3);

        buttons.get(2).click();
        BrowserUtils.wait(3);
        Alert alert = driver.switchTo().alert();

        alert.sendKeys("Hello,World!");
        alert.accept();//click ok

        String expected3 = "Hello,World!";
        String actual3 = driver.findElement(By.id("result")).getText();

        if(actual3.endsWith(expected3)){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
            System.out.println("Expected : " + expected3);
            System.out.println("Actual : " + actual3);

        }



        BrowserUtils.wait(3);
        driver.quit();


    }
}
