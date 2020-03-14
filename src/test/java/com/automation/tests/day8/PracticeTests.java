package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PracticeTests {

    /**
     * Create a class called PracticeTests
     *  --setup before/after methods
     *    -- in before method. instantiate webdiver and naviate to http://practice.cybertekschool.com/
     *    -- in after method---just close webdriver
     *
     * --create a test called loginTest
     *   --go to "Form Authentication" page
     *   --enter valid credentials
     *     username:tomsmith
     *     password:SuperSecretPassword
     *   --verify that following sub-header message is displayed "Welcome to the Secure Area. When you are done click logout below."
     */

    private WebDriver driver;

    /**
     * We put @Test annotation to make methods execcutable as tests
     */
       @Test
       public void loginTest(){
           driver.findElement(By.linkText("Form Authentication")).click();
           BrowserUtils.wait(3);
           driver.findElement(By.name("username")).sendKeys("tomsmith");
           driver.findElement(By.name("password")).sendKeys("SuperSecretPassword", Keys.ENTER);

           BrowserUtils.wait(3);

           String expected  = "Welcome to the Secure Area. When you are done click logout below.";
           String actual = driver.findElement(By.className("subheader")).getText();
           //if assertion fails ---it will throw exception and message will be printed
           //3 parameters : (expected ,actual ,message in case of error)

           Assert.assertEquals(actual,expected,"Sub-header message is not matching!");
       }

    @BeforeTest
      public void setup(){
          WebDriverManager.chromedriver().version("79").setup();
          // Interview question : HOW TO HANDLE SSL ISSUES IN SELENIUM ? THEN ANSWER IS BELOW  4 LINES
          //ChromeOptions--use to customize browser for tests
        //ChromeOptions chromeOptions = new ChromeOptions();
        //to ignore "Your connection is not private issue"
       // chromeOptions.setAcceptInsecureCerts(true);
        //provide chromeOptions object into chromedriver constructor
          driver = new ChromeDriver();
          driver.get("http://practice.cybertekschool.com/");
          driver.manage().window().maximize();
      }

      @AfterTest
      public void teardown(){
         driver.quit();
      }




      }



