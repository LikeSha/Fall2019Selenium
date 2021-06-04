package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

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
     *   --verify that following sub-header message is displayed
     *   "Welcome to the Secure Area. When you are done click logout below."
     */

    private WebDriver driver;

    /**
     * We put @Test annotation to make methods executable as tests
     */
       @Test
       public void loginTest(){
           driver.findElement(By.linkText("Form Authentication")).click();
           BrowserUtils.wait(5);
           driver.findElement(By.name("username")).sendKeys("tomsmith");
           driver.findElement(By.name("password")).sendKeys("SuperSecretPassword", Keys.ENTER);

           BrowserUtils.wait(5);

           String expected  = "Welcome to the Secure Area. When you are done click logout below.";
           String actual = driver.findElement(By.className("subheader")).getText();
           //if assertion fails ---it will throw exception and message will be printed
           //3 parameters : (expected ,actual ,message in case of error)

           Assert.assertEquals(actual,expected,"Sub-header message is not matching!");



       }

    /**
     * Given user is on the practice landing page
     * when user navigates to "Forgot password" page
     * then user enters his email
     * and clicks "Retrieve password" button
     * Then user verifies that message " Your e-mail's been sent!" is displayed
     */
    @Test
       public void forgotPasswordTest(){
           driver.findElement(By.linkText("Forgot Password")).click();
           BrowserUtils.wait(5);
           driver.findElement(By.name("email")).sendKeys("tradersalike88@yahoo.com", Keys.ENTER);


           BrowserUtils.wait(5);

        String expected = "Your e-mail's been sent!";
        String actual = driver.findElement(By.name("confirmation_message")).getText();
        Assert.assertEquals(actual,expected,"Confirmation message is not valid");


       }

    /**
     * TASK for 5 minutes
     * Given user is on the practice landing page
     * When user navigates to "Checkboxes" page
     * And clicks on checkbox #1
     * Then user verifies that checkbox #1 is selected
     */

    @Test
    public void CheckboxTest1(){
           driver.findElement(By.linkText("Checkboxes")).click();
           //locator for specific checkbox, xpath://input[1], cssSelector: input:nth-of-type(1)

        // collect all checkboxes
           BrowserUtils.wait(5);
        List<WebElement> checkboxes = driver.findElements(By.tagName("input"));
        BrowserUtils.wait(5);

        checkboxes.get(0).click(); // to click on 1st checkbox

        Assert.assertTrue(checkboxes.get(0).isSelected(),"Checkbox #1 is not selected!");

        BrowserUtils.wait(4);


    }

    @BeforeTest
      public void setup(){
          WebDriverManager.chromedriver().setup();
          // Interview question : HOW TO HANDLE SSL ISSUES IN SELENIUM ? THEN ANSWER IS BELOW  4 LINES
          //ChromeOptions--use to customize browser for tests
        //ChromeOptions chromeOptions = new ChromeOptions();
        //to ignore "Your connection is not private issue"
       // chromeOptions.setAcceptInsecureCerts(true);
        //provide chromeOptions object into chromedriver constructor
        //driver = new ChromeDriver(chromeOptions);
          driver = new ChromeDriver();
          driver.get("http://practice.cybertekschool.com/");
          driver.manage().window().maximize();
      }

      @AfterTest
      public void tearDown(){
         driver.quit();
      }




      }

     /*
March 14, 2020

Agenda :TestNG

Level of testing :

unit---testing of smallest functional piece of the application , for example  method or class ....

integration
system
UAT

In my project , developers were following this flow:

developers write a code -->unit tests to test code-->code review-->new build can be deployed to the test env
--->and here we do functional testing

who write unit tests ? For application code --developers.

Can I write some unit tests for my automation framework ? yes

import org.tesng.annotations.Test
@Test annotation used to crate a test. put it on top of the method.In this case ,
we don't use main method and we can create lots in one class.

 BEFORE SUITE

    BEFORE TEST

     BEFORE CLASS

         BEFORE METHOD
             TEST 1
         AFTER METHOD

          BEFORE METHOD
            TEST 2
          AFTER METHOD

      AFTER CLASS

   AFTER TEST

 AFTER SUITE

 SUITE ---it's a collection of tests. For example : regression suite.

 TestNG has 2 types of assertions : hard and soft

 Assertion class provides hard assertions

 What's the difference ? if hard assertion fails --test execution stops due to exception.
 In case of soft assertion,---test execution doesn't stop.
 For soft assertions, you can use the class---SoftAssert. But, common practice is to use only hard assertions.
  Junit, for example, has only hard assertions.

 We use annotation to create tests and organize them.

 Create a class called PracticeTests
 --setup before/after methods
   -- in before method. instantiate webdiver and naviate to http://practice.cybertekschool.com/
   -- in after method---just close webdriver

--create a test called loginTest
  --go to "Form Authentication" page
  --enter valid credentials
    username:tomsmith
    password:SuperSecretPassword
  --verify that following sub-header message is displayed "Welcome to the Secure Area.
  When you are don click logout below"

  ##############################################################################

  TestNG --it's a unit testing , functional testing, e2e testing tool.
  In test automation we use TestNG along with Selenium Webdriver to develop UI test automation scripts.
  TestNG was inspired by Junit (another very popular unit testing framework).
  TestNG has :

          -annotations for more complex test, like :@BeforeSuite, @BeforeClass, @BeforeMethod,@Test, etc.....
          -allows to create test suits with xml runners
          -has in-build HTML report
          -allows to group tests
          -allows to do Data Driven Testing


            // Interview question : HOW TO HANDLE SSL ISSUES IN SELENIUM ? THEN ANSWER IS BELOW  4 LINES

        //ChromeOptions--use to customize browser for tests

          ChromeOptions chromeOptions = new ChromeOptions();

       //   to ignore "Your connection is not private issue"

          chromeOptions.setAcceptInsecureCerts(true);
     //   provide chromeOptions object into chromedriver constructor
          driver = new ChromeDriver(chromeOptions);



           //if assertion fails ---it will throw exception and message will be printed
           //3 parameters : (expected ,actual ,message in case of error)

           Assert.assertEquals(actual,expected,"Sub-header message is not matching!"
           the sentence "Sub-header message is not matching!" is we added ,
           just in case assertion fails , we want to print some message out .

           NullPointerException means : you are trying to use object what wasn't instantiated !
           just like you are trying to drinking water but it doesn't exist.

 */

