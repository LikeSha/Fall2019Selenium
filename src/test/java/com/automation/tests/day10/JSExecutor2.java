package com.automation.tests.day10;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor2 {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrme");
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void verifyTitle(){
        String expected = "Practice";
        //we create javascriptexecutor object by casting webdriver object
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //executeScript ---this method exectues javascript code
        //we provide js code as a String
        //return document.title--javascript code
        //document ---represnets HTML page
        //.toString()--to avoid additional casting from Object to String
        String actual = (String) js.executeScript("return document.title").toString();

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void clickTest(){
        WebElement link = driver.findElement(By.linkText("Multiple Buttons"));
        // disable this click action, to perform it with js executor

//        link.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //after "" you can list webelements that will be used
        //as part of your javascript code
        //it's varargs,so you can list 0+
        //arguments--listed after ,
        //use index to get specific webelement
        //WebElement arguments - {element,link, link2)
        //from left --to right
        js.executeScript("arguments[0].click()",link);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
