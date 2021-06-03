package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.input.BrokenInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests {

    private WebDriver driver;

    @Test
    public void googleSearchTest(){
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("java", Keys.ENTER);
        BrowserUtils.wait(2);
        //since every search item has a tag name <h3>
        //it's the easiest way to collect all of them
        List<WebElement> searchItems = driver.findElements(By.tagName("h3"));
        for(WebElement searchItem : searchItems){
            String var = searchItem.getText();
            //if there is a text --print it
            if(!var.isEmpty()){
                System.out.println(var);
                //verify that every search result contains java
                //is some of the search results
                //doesn't contain java word, it will fail the test
                Assert.assertTrue(var.toLowerCase().contains("java"));
                //test without assertion is useless - what makes test => test
                //without assertion you can not understand test has passed or failed
                System.out.println(var.toLowerCase());
                System.out.println();
            }

        }
    }

    /**
     * BELOW FEW LINE SENTENCES ARE " TEST SCENARIO" IN TESTNG FRAMEWORK
     * Given user is on the amazon.com page
     * when user enters "Java" as a search item
     * then user clicks on the search button
     * and user clicks on the first search item
     * and user verifies that title of the search item contains "Java"
     * above are test scenarios we made
     */
    @Test(description = "Search for Java book on amazon")
                public void amazonSearchTest(){
        driver.get("http://amazon.com");
        //there is a chance that item is not visible
        //so we need to maximize window before clicking on it
        driver.manage().window().maximize();

        BrowserUtils.wait(5);

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java", Keys.ENTER);
        BrowserUtils.wait(5);
        //find all links inside h2 elements, because h2 element is no clickable itself
        //hyperlinks must be clickable
        List<WebElement> searchItems = driver.findElements(By.xpath("//h2//a"));

        //click on the first item
        for(WebElement searchItem: searchItems){
            System.out.println("Title: "+searchItem.getText());
        }
        searchItems.get(0).click();
        BrowserUtils.wait(5);

        WebElement productTitle = driver.findElement(By.id("productTitle"));
        String productTitleString = productTitle.getText();
        System.out.println(productTitleString);

        Assert.assertTrue(productTitleString.contains("Java"));

        //so h2 elements are not clickable, even though they contain links
        //that's why, instead of collection all h2 elements
        //we collected all hyperlinks
        //every hyperlink represent some search item
                }



    @BeforeMethod
    public void setup(){
        //setup webdriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown(){
        //close browser and destroy webdriver object
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