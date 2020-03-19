package com.automation.tests.day9;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor {
    private WebDriver driver;


    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
    }


    @AfterMethod
        public void tearDown(){
            driver.quit();
        }

        @Test
    public void scrollTest(){
        driver.get("http://practice.cybertekschool.com/infinite_scroll");
            JavascriptExecutor js = ()
        }



}





