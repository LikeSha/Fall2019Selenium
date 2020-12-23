package com.automation.tests.day9;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor {
 //   private WebDriver driver;
    private RemoteWebDriver driver;



    @BeforeMethod
    public void setup(){
       // driver = DriverFactory.createDriver("chrome");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }


    @AfterMethod
        public void tearDown(){
          BrowserUtils.wait(3);
        driver.quit();
        }

        @Test
    public void scrollTest(){
        driver.get("http://practice.cybertekschool.com/infinite_scroll");
 //         you need to cast if reference type is a WebDriver
//            JavascriptExecutor js = (JavascriptExecutor) driver;
            //scroll down 250 pixels
            // x,y offset
            for(int i= 0; i<10; i++){
                driver.executeScript("window.scrollBy(0,250)");
                BrowserUtils.wait(1);
            }


            BrowserUtils.wait(3);
        }

        @Test
        public void scrollToElementTest(){
          driver.get("http://practice.cybertekschool.com/");
          driver.manage().window().maximize();
          BrowserUtils.wait(2);
            WebElement link = driver.findElement(By.linkText("Cybertek School"));
            //scrollIntoView ---javascript method
            //arguments[0]--means 1st webelement after comma
            //in this case arguments[0] is reffering to link, because link is the first element
            // after comma, the comma is this script ( piece of code) comma :
            //  "arguments[0].scrollIntoView(true)",link); not means
            //website comma ! it the comma inside this syntax : "arguments[0].scrollIntoView(true)",link
            // as we can see ,it is the comma before the word " link" and after (true)  (true),link
            driver.executeScript("arguments[0].scrollIntoView(true)",link);
        }


}





