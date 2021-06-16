package com.automation.tests.vytrack.login;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//STATIC IMPORT OF ALL ASSERTIONS
import static org.testng.Assert.*;

public class LoginPageTests {
    //why we store the variable private ? in our case its ok if you keep it as public .access modifier
    //doesn't change anything here in our case here.
    // We do it private simply because :
    //its just common practice when you have variables or methods that would not be used outside of this class
    //the common practice is to keep them as private ,this is Encapsulation
    private WebDriver driver;
    //https is a secured version of http protocol
    //http-its hypertext transfer protocol that every single website is using now days
    // https--data encrypted ,no change for hackers to retrieve info
    //http-- data as a plain text, very easy to hack it
    private String URL = "https://qa2.vytrack.com/user/login";
    //credentials for store manager
    private String username = "storemanager85";
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy =By.id("prependedInput2"); //By is a class--> when we find an element by using By.... methods
    ////it returns By data type and we stored it in to By as above statement.
    // > in css Selector means same thing as / in xpath ,go to direct child
    private By warningMessageBy = By.cssSelector("[class='alert alert-error'] > div");
    //"[class='alert alert-error'] > div" <<--- this greater sign( >) this means direct go to next one child ,
    // not go to all grandchilren
    //because that warning message text is inside the child <div>
    //just like xpath "/"  function

    // you can also right click the warning message text , and select copy --> copy xpath
    // then use By.xpath("//*[@id="login-form"]/fieldset/div[1]/div"); as below ,also works
    //private By warningMessageBy = By.xpath("//*[@id="login-form"]/fieldset/div[1]/div");


    @Test(description = " verify that warning message displays when user enters invalid username")
    public void invalidUsername(){
          driver.findElement(usernameBy).sendKeys("invalidusername");
          driver.findElement(passwordBy).sendKeys("UserUser123", Keys.ENTER);
        BrowserUtils.wait(3);
        WebElement warningElement = driver.findElement(warningMessageBy);
        assertTrue(warningElement.isDisplayed());

        String expected = "Invalid user name or password.";
        String actual = warningElement.getText();
        assertEquals(actual,expected);
    }

    @Test(description = "Login as store manager and verify that title is equals to Dashboard")
    public void loginAsStoreManager(){
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password,Keys.ENTER);
        BrowserUtils.wait(5);

        String expected = "Dashboard";
        String actual = driver.getTitle();
        //how can you find title element ( tag) in a page ?
        //just type <title> in the search bar( ctrl+F)
        // normally title are under the <head> tag on the top

        assertEquals(actual,expected,"Page title is not correct!");

        /*
           html5 warning message attribute
           this warning message can not be catched with locator
           it some kind of attribute coming from html
          you can not catch it, there is a method getAttribute
          you can just read it
         */


    }




    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(){
        //if webdriver object alive
        if(driver !=null){
            //close browser, close session
            driver.quit();
            //destroy driver object for sure ,by doing so ,Java can release memory ,( don't save garbage data)
            driver = null;
        }
    }

}
/*
Today is March 15,2020

    Agenda
       TestNG
       Locators : Xpath CSS Selector

       Question ? VERY FAMOUS INTERVIEW QUESTIONS

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

       qa1.vyrack.com/user/login
       qa2.vyrack.com/user/login
       qa3.vyrack.com/user/login

       under tests package create a package called "vytrack" ( qa2.vyrack.com/user/login)--->
       under this package create a package called "login"

       by using cssSelector : "[class='alert alert-error'] > div"  <---- this means direct go to next one child ,
        not go to all grandchildren

       in css selector ,you can move from parent to child element (node) ,
       but you can not move node from child to parent like in xpath

       //div[@class='example']/div--go to div element , that is direct child of this element (only one step down)
       //div[@class='example']//div==go to div element, that is any child (grand child,
      grand grand child) of this element ( no limit for strpping down)

       CSS SELECTOR

 div[class='example'] > div ---go to div element, that is direct child of this element (only one step down) Greater (>) symbol.

 div[class=‘example’] div - go to div element, that is any child (grand child, grand grand child)
 of this element (no limit for stepping down). Just space.

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

/*
March 28,2020 POM

Agenda:
   --Test base class
   --Page Object Model
   --Page Factory

   ################################################################

   Implicit wait doesn't get along with Explicit wait. If you gonna use them in the same test,
   it might cause unexpectedly long delays.

   If we would use implicit wait- it would go into @BeforeMethod
   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

   Explicit wait - used inside a test itself , Since we have to provide a WebElement,
   unless @BeforeMethod use some kind of WebElements.


   Test base -- abstract class that is used as a basement for all test classes.
   This class provides minimum essential setup and cleanup for every test of our project.
   Class is abstract because has to be extended.Doesn't contain any test itself.
   In this class, we initialize WebDriverWait, Actions class objects and open the browser.
   Should be stored under tests package or some kind of base package.
   But not under the utilities package. We can also add @Before/After test, class,suit methods if needed.

   why test base class not store in utility package ? because its NOT Utility.its a test base class!!
   under utilities package what classes you can have :
   String utility, datetime utility,fileutility,browserutility, APIutility, databaseutility......
   they are reusabble collections and reusable methods.!
   test base class its not a class with reusable methods !
   it is a class work as a basement. So it is a proto desk class,
   YOU DON'T CALL ANY SINGLE METHOD FROM THIS CLASS ,
   YOU DON'T INSTANTIATE THIS CLASS. SO IT SHOULDN'T GO UNDER THE UTILITY PACKAGE.
   IT IS A BLUE PRINT FOR ALL TESTS CLASSES.

   package com.automation.tests.vytrack;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import com.automation.utilities.ExcelUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public abstract class AbstractTestBase {
    //will be visible in the subclass , regardless on subclass location(same package or no)
    protected WebDriverWait wait;
    protected Actions actions;

    @BeforeMethod
    public void setup(){
        String URL = ConfigurationReader.getProperty("qa3");
        Driver.getDriver().get(URL);
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(),15);
        actions = new Actions(Driver.getDriver());

        }

        It's a blueprint for all test classes

        Singleton class feature :

        Singleton class that has private static instance with private constructor ,
        public getter method that return the instance .
        singleton class does't not apply for test base class ,
        because test base class does't have static variable ,and does't have private constructor.
         in Fall2019Selenium project ,our Driver class is singleton class( under utilities package)

        in the test class, we should keep it minimum logic ,minimum codes as minimum as we can ,
        thats why we created test base class ,all test classes extends test base class.

        POM  PAGE OBJECT MODEL

        Test case become short and optimized as we are able to resue page object methods in the POM classes.

        Any change in UI can easily be implemented , updated and maintained into the Page Objects and Classes.

        PageFactory===used to improve page object model.
        It helps to find webelements with @FindBy, @FindBys, @FindAll


      simple framework as follows :

      pages
          AbstractPageBase
          LoginPage
          VehiclesPage
      tests
          AbstractTestBase
          LoginTests
          VehiclesTest
      utilities
          BrwoserUtils
          ConfigurationReader
          Driver
          DateTimeUtilities *optional
          StringUtilities   *optional
          DataBaseUtilities *optional


AbstractPageBase --works as basement for all/most of the page class
--Contains common element/locators
--Initializes page factory-->to use @FindBy in all sub-classes

AbstrackTestBase-- works as a basement for all test classes
--contains @Before and @After methods, no tests at all
--opens browser
--closes browser


Why they are Abstract ?

because , they have to be extended. If class is abstract, it must be extended, otherwise you cannot use it .

Why WebElement are private in page classes?

--so there is no way to use the directly in the tests.
We have to seperate page logic from the test. Otherwise we will have too much logic in the test classes.
test classes are not responsible for find element and their interacion. Its a role of page classes.
In test classes, we just create a page objects and call methods to interact with page to perform testing.
Also, we use assertions in the test classes.

 */
