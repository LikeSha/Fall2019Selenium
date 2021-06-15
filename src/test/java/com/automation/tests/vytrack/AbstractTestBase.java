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
    //We use protected access modifier is because :
    // will be visible in the subclass , regardless on subclass location(same package or no)
    // protected visible : inside class, same Package Class,Same Package Sub-Class,Other Package Sub-Class
    // protected ONLY NOT VISIBLE IN Other Package Class.
    protected WebDriverWait wait;
    protected Actions actions;

    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest test;

    protected static int row = 1;
    protected ExcelUtil excelUtil;



    //@Optional -- to make parameter optional
    //if you don't specify it , testng will require to specify this parameter for every test , in xml runner

    @BeforeTest
    @Parameters("reportName")
    public void setupTest(@Optional String reportName){
        System.out.println("Report name : " + reportName);
        reportName  = reportName==null?"report.html":reportName+".html";
        //if the report name is provided in testng xml file;
        //use that file to create report based on that report name
        //else create report name as default


        report = new ExtentReports();

        String reportPath = "";
        //location of report file
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            reportPath = System.getProperty("user.dir")+"\\test-output\\"+ reportName;
        }else{
            reportPath = System.getProperty("user.dir")+"/test-output/" + reportName;
        }
        //is a HTML report itself
        htmlReporter = new ExtentHtmlReporter(reportPath);
        //add it to the reporter
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("VYTRACK Test Automation Result");
    }

     @AfterTest
    public void tearDownTest(){
        report.flush();//to release a report
    }

    @BeforeMethod
    public void setup(){
        String URL = ConfigurationReader.getProperty("qa3");
        Driver.getDriver().get(URL);// get() method is coming from WebDriver();
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(),15);
        actions = new Actions(Driver.getDriver());



    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) throws IOException {
        //ITestResult class describes the result of a test
        //if test failed ,take a screenshot
       if(iTestResult.getStatus()==ITestResult.FAILURE){
           //screenshot will have a name of the test
           String screenshotPath = BrowserUtils.getScreenshot(iTestResult.getName());
           test.fail(iTestResult.getName());//attach test name that failed
           BrowserUtils.wait(2);
           test.addScreenCaptureFromPath(screenshotPath, "Failed");//attach screenshot
           test.fail(iTestResult.getThrowable());//attach console output
           //if excelUtil object was created
           //set value if result column to failed
           if(excelUtil !=null){
               excelUtil.setCellData("FAILED","result",row++);
           }

       }
        BrowserUtils.wait(2);
        Driver.closeDriver();
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
   Class is abstrct because has to be extended.Doesn't contain any test itself.
   In this class, we initialize WebDriveWait, Actions class objects and open the browser.
   Should be stored under tests package or some kind of base package.
   Bust not under the utilities package. We can also add @Before/After test, class,suit methods if needed.

   why test base class not restore in utility package ? because its NOT Utility.its a test base class!!
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
