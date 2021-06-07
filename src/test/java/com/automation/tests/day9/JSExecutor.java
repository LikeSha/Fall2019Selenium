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

/*
March 18,2020

     Agenda:
       Actions class
       JavascriptExecutor

       //moveToElement returns instance of action class that's why we can chain them

       //builder pattern => you put one method then you can take action
//builder pattern == chaining methods

//what is build?
//if you have multiple actions you have to put build
//to combine a couple of actions.

//build() is needed when you have couple of actions
//build combines the action; perform; starts the action
//in this example; first we move to one image then second so we used build
//always end with perform
//perform does not click, it starts the action, execute the event
//you can perform click, drag and drop etc
//actions class has different implementations
//moveToElement returns instance of action class that's why we can chain them


Why do we need JavaScriptExecutor?
In Selenium Webdriver, locators like XPath, CSS, etc. are used to identify and perform operations on a web page.
In case, these locators do not work you can use JavaScriptExecutor.
You can use JavaScriptExecutor to perform an desired operation on a web element.

//how to use javaScriptExecutor?
//javaScriptExecutor; it is an interface we can not create object out of it.
//But javascript executor and webDriver are like siblings
//So we will cast driver to JavascriptExecutor
//we convert webDriver object into JavaScriptExecutor
//JavascriptExecutor js = (JavascriptExecutor) driver;
//interface => they don't have implementation
//if you have interface as reference type you can see methods only coming from that interface
//you can not see other methods that are in other interfaces
//so we will use remoteWebDriver class as reference type :
//if you use remoteWebDriver class as reference type you do not need to cast anymore, it has everything
//like this => private RemoteWebDriver driver;
            //driver.executeScript("window.scrollBy(0, 250)");
//you need to cast if your reference type is webDriver; like this
=>  private WebDriver driver;
    //JavascriptExecutor js = (JavascriptExecutor) driver;

    Actions builder = new Actions(driver)
    builder.click(tableRows.get(1)).keyDown(Keys.CONTROL).click(tableRows.get(2).keyUp(Kyes.CONTROL)
    .build().perform();
 */



