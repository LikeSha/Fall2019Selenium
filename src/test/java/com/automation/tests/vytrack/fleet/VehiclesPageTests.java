package com.automation.tests.vytrack.fleet;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VehiclesPageTests {

    /**
     * under fleet package create a class called VehiclesPageTests
     *
     *  in this class. you will need to create @BeforeMethod with setup and @AfterMethod with tearDown part.
     *  Use LoginPageTests class as a reference.
     *
     * create a test called verifyPageSubTitle
      -- in this test , you will need to navigate to Fleet ---->Vehicles and verify that page subtitle is equals to "All Cars"
     *
     *   user assertions for validation.
     */

    private String URL = "https://qa2.vytrack.com/user/login";
    //    CREDENTIALS FOR store manager
    private String username = "storemanager85";
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By fleetBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet')]");
    //or like this : private By fleetBy = By.xpath("//*[@class='title title-level-1' and contains(text(),'Fleet')]");
    //* stands for any tag name .
    private By subtitleBy = By.className("oro-subtitle");
    private By pageNumberBy = By.cssSelector("input[type='number']");
    // if we use xpath : //label[text()='Page:']/..//input  ( .. means go back to find the parent element)
    // this method is based on first we looking for "page" element next to the number 1, we located number 1
    //element based on "page" element which is next to the number 1 locator on the UI page

    private WebDriver driver;
    @Test
    public void verifyPageSubTitle(){
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);

        BrowserUtils.wait(5);
        //click on fleet
//        driver.findElement(fleetBy).click();
        //BrowserUtils.wait(2);
        //driver.findElement(By.linkText("Vehicles)).click();

        // Actions class is used for more advanced browser interactions
        Actions actions = new Actions(driver);
        //move to element instead of click , ACTIONS actually is telling browser hover over on page.
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        //perform -- to execute command
        //every action should end with perform()
        BrowserUtils.wait(2);
        //click on Vehicles
        driver.findElement(By.linkText("Vehicles")).click();
        BrowserUtils.wait(5);


//###############################################################################
       // find subtitle element
        WebElement subTitleElement = driver.findElement(subtitleBy);
        System.out.println(subTitleElement.getText());

        String expected = "All Cars";
        String actual = subTitleElement.getText();

        Assert.assertEquals(actual,expected);

    }

    /**
     * one more task :
     *           Given user is on the vytrack landing page
     *           When user logs on as a store manager
     *           Then user navigates to Fleet-->Vehicles
     *           And user verifies that page number is equals to "1"
     */

      @Test
      public void verifyPageNumber(){

           String expected = "1";
           String actual = driver.findElement(pageNumberBy).getAttribute("value");


          Assert.assertEquals(actual, expected);




      }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();


        // below block of code we copy paste from the first test verifyPageSubTitle in order to
        // no repeat right them in our second test , since we put the first block of code
        //into BeforeMethod , that's why our second test just 3 lines of code
        //BeforeMethod not only write WebDriver, get url...it can be a lot of code.
      //-----------------------------------------------------------------------------------
//        driver.findElement(usernameBy).sendKeys(username);
//        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
//
//
//
//        BrowserUtils.wait(5);
//        //click on fleet
////        driver.findElement(fleetBy).click();
//
//        // Actions class is used for more advanced browser interactions
//        Actions actions = new Actions(driver);
//        //move to element instead of click
//        actions.moveToElement(driver.findElement(fleetBy)).perform();
//        //perform -- to execute command
//        //every action should end with perform()
//        BrowserUtils.wait(2);
//        //click on Vehicles
//        driver.findElement(By.linkText("Vehicles")).click();
//        BrowserUtils.wait(5);
    }
    @AfterMethod
    public void teardown() {
        //if webdriver object alive
        if (driver != null) {
            //close browser, close session
            driver.quit();
            //destroy webdriver object for sure
            driver = null;
        }
    }



}


/*

-                                // to find out link xpath , for example if you see element like this :
-                                // <a href="/context_menu">Context Menu</a>==$0
-                                // we can input this  : //a[.='Contex Menu']
-
-
-
-
- //how can you find title element ( tag) in a page ?
-        //just type <title> in the search bar( ctrl+F)
-        // normally title are under the <head> tag on the top
 */

/*
You have 500 test cases but the client asked you to run only 40 of them for smoke test,
       How will you do this ?

       Answer : So we use xml runners to create suite of tests and we already have xml runner for the smoke test .
       In my framework,we group test scrips based on modules.
       So for every module we have a corresponding package with test scripts.
       Usually we create separate classes for smoke classes,
       and store all the smoke test in one place to manage easily.


       Log file : in computing, a log file is a file that records either events that
       occur in an operating system or other software runs,
       or messages between different users of a communication software.
       Logging is the act of keeping a log. ... Many operating systems,
       software frameworks and programs include a logging system => google

       How do I start automation?

   First I create test cases and exectue test scenarios manually. It helps understand the logic of scenarios.
   Then, I have to find all webelements and related methods to them. Then I start developing automation scripts.
   Last step is ---generate report ( to provide evidences of my test results,
   user friendly, html report have all steps and results in case of failure,
   stacktrace ,and screenshot of failures , so I can use this report to demonstrate and explain what happened)

   #######################################################

   Scenario : Verify for store manager

   regarding this test ,I am gonna create two scenarios , under vytrack package ,
   we create another package named "activities" , then we create two classes ,
   one is CallsPageTests, another one is CalendarEentsPageTest , as we can see ,
   we create package based on module ! in another word, each module owns one package ,
   that package exclusively for that module, so that our tests are super clear ,
   we saved our test scripts according to different module .

   Login as story manager
   Go to Activites -->Calls
   Verify that Log Call button is displayed

   Go to Activities -->Calendar Events
   Verify that Create Calendar Event button is displayed

 */

