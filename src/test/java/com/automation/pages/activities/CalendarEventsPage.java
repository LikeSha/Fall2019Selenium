package com.automation.pages.activities;

import com.automation.pages.AbstractPageBase;
import com.automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

// object repository -- page package--> where is your page classes -->
// from page classes you create page objects. page objects created in the correspondence test class !
//In test classes, we just create a page objects and call methods to interact with page to perform testing.
// Also, we use assertions in the test classes.

public class CalendarEventsPage extends AbstractPageBase {

    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(className = "select2-chosen") // in inspect area ctrl+F we input: .select2-chosen ,this is css selector
    private WebElement owner;             // . in cssSelector means className # means id

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    private WebElement startDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    private WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    @FindBy(css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame;



    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    @FindBy(id="tinymce")
    private WebElement descriptionTextArea;

    @FindBy(css = "[class='btn-group pull-right'] > button") // > means direct go to next tag button, in this
    // case ,if we don't write > button, then it will select Save And Close tab + the drop down arrow next to it.
    private  WebElement saveAndClose;

    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//div")
    private WebElement generalInfoDescription;


    public void enterCalendarEventTitle(String titleValue){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
    }

    public void enterCalendarEventDescription(String description){
        //wait until frame is available and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        descriptionTextArea.sendKeys(description);
        driver.switchTo().defaultContent();//exit from the frame

    }

    public void clickOnSaveAndClose(){
        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
      //  BrowserUtils.clickWithJS(saveAndClose); // or we can use saveAndClose.click();
    }

   public String getGeneralInfoTitleText(){
        BrowserUtils.waitForPageToLoad(20);
        return generalInfoTitle.getText();
   }

   public String getGeneralInfoDescriptionText(){
        BrowserUtils.waitForPageToLoad(20);
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Description']/following-sibling::div//div")));
        return generalInfoDescription.getText();
   }

    public List<String> getColumnNames(){
        BrowserUtils.waitForPageToLoad(20);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[")));
        return BrowserUtils.getTextFromWebElements(columnNames);
    }



    public String getStartTime(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }

    public String getEndTime(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }



    public String getOwnerName(){

        BrowserUtils.waitForPageToLoad(10);
        //wait for element to be present in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
        //why we put current user element under base page class but owner element under create calendar event
        //page class?  because: "current owner" element belongs to top menu! Top menu , navigation menu
        //doesn't belong to particular page.Since top menu elements are shared, we can store them
        //into the base page class.
    }

    public void clickToCreateCalendarEvent(){

        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtils.waitForPageToLoad(20);

    }

    public String getStartDate(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        return startDate.getAttribute("value");
    }


}
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

/*
Why we put current user element undr base page class and owner element under createCalendarEvent page class?

current user element belongs to top menu.Top menu, navigation menu doesn't belong to particular page.
Since top menu elements are shared, we can store them in the base page class.
 */

/*
April 4,2020

   Agenda :
             data provider
             testng xml runners
             maven surefire plugin
             test execution over terminal with maven commands
             test parametrization
             summary of testng framework

   Tomorrow :
            Practice session, no new topics
            Building a framework for every simple application and creating some test scripts

   Tuesday : BDD and Cucumber

   #########################################################################################

   Data Driven Testing (DDT) --- it's when your test data is separate  from your code .
   Basically, you don't store test data in variables . where this data can be stored ?
   In excel and csv files, in database. In terms of cucumber bdd framework----feature files.

   To read excel files, there is java library that calls apache poi, We gonna cover it in this course .
   In terms csv files, it's a plain text file where values are sparated by comma. To read it ,
   you can use Scanner. But, there are more advanced libraries, like Open CSV.

   To connect from java to data base, there is JDBC api.

   Why do we need DDT ? It allows us to run same script with different test data

   TestNG natively supports DDT through Data Provider.

   Data Provider--allows to run same test multiple times with different test sets.

   For example : you have tests cases where you to create cars. And you need to create like 20 of them.
   Steps are the same, the only difference is --test data. So instead of creating 20 identical test scripts,
   we can just create a one , and supply that test with a test data from data provider
   -----This example is from vytrack project . fleet--vehicles--create car



   //With enterCalendarEventDescription method in page class;
//you do not need to switch frame inside test,
//everything is covered is covered inside page; all kind of page interactions

############################################################################################################
below is example how to connect dataProvider to your test :

 @Test(dataProvider = "credentials")
    public void loginWithDDT(String userName, String password) {
        test = report.createTest("Verify page title as " + userName);
        LoginPage loginPage = new LoginPage();
        loginPage.login(userName, password);
        test.info("Login as " + userName);//log some steps
        BrowserUtils.wait(2);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
        test.pass("Page title Dashboard was verified");
    }
    //Object[][] or Object[] or Iterator<Object[]>
    //Object[]----1 column with a data
    //Object[][] 2+
    //now line 52 and below the block of codes connected to your dataprovider (line 66 to73)
    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {"storemanager85", "UserUser123"},
                {"salesmanager110", "UserUser123"},
                {"user16", "UserUser123"}
        };
    }

######################################################################################################
--createa a page object inside a test itself

Framework that implements Data Driven Testing calls---Data Driven Framework

###########################################################

testng xml runners

testng.xml -->xml file that is used to create test suits.

what kind of test suits ? for example : major regress test suit, ( including all test cases );
we can selectively test some components; we can create smoke test suit.

xml file runner helps us to select tests from different classes and group them ,and create test suit.

xml---file extension, for example pom.xml file

what's the name of test ng xml runner file ?

    name can be anyting , like : smoke.xml , regression.xml,  fleet.xml, but default name is testng.xml

    how many xml runners we can create ? as many as we need

    what are the requirements for xml runner file ?
       first of all, it should start with one line, that specifies document type :

       <!DOCTYPE suite SYSTEM "https://tesn.org/testng-1.0.dtd">

       source : https://testng.org/doc/documentation-main.html#parameters-dataproviders

       ##################################################################

       ElementClickIntercepteException---something was clicked instead of your element.Put more wait tiem.
       NoSuchSessionExceptiondriver object was called but not created.


 */
