
package com.automation.tests.vytrack.activities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalendarEventsPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.DateTimeUtilities;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class NewCalendarEventsTests extends AbstractTestBase {
    LoginPage loginPage = new LoginPage();
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

    /**
     * Test Case: Default options
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Default owner name should be current user/
     **/

    /**
     * If you wanna interact with any page ( test any page) you need to create page object !
     * that means : if you wanna interact with login page ,you need to first create login page object
     * if you want to interact with calendarEventsPage, you need to create calenderEventPage object !
     * page objects created in the correspondence test class !
     * In test classes, we just create a page objects and call methods to interact with page to perform testing.
     * Also, we use assertions in the test classes.
     */
    @Test
    public void defaultOptionsTest() {
        test = report.createTest("Verify default login options");

        LoginPage loginPage = new LoginPage();// if I want to interact with that page ,I create that page object!
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();// if I want to interact with that page ,I create that page object!

        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();

        Assert.assertEquals(calendarEventsPage.getOwnerName(), calendarEventsPage.getCurrentUserName());
        //calendarEventsPage.getCurrentUserName() this method is coming from AbstractPageBase. the code
        //is in there, because CalendarEventsPage extends AbstractPageBase, thats why we can use it
        //in CalendarEventsPage method.

        String actualStartDate = calendarEventsPage.getStartDate();
        String expectedStartDate = DateTimeUtilities.getCurrentDate("MMM d, yyyy");

        Assert.assertEquals(actualStartDate, expectedStartDate);

        test.pass("Default options verified");

    }

    /**
     * 35 minutes until 4:05
     * Test Case: Time difference
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Verify that difference between start and end time is 1 hour
     **/

    @Test
    public void timeDifferenceTest() {
        test = report.createTest("Verify time difference");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        loginPage.login();

        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();

        String startTime = calendarEventsPage.getStartTime(); //get start time
        String endTime = calendarEventsPage.getEndTime(); //get end time
        String format = "h:mm a";//format 5:15 AM for example

        long actual = DateTimeUtilities.getTimeDifference(startTime, endTime, format);

        Assert.assertEquals(actual, 1, "Time difference is not correct");

        test.pass("Time difference verified");

    }

    /**
     * Test Case: Verify calendar events table
     * Login as store manager
     * Go to Activities --> Calendar Events
     * And verify that column names displayed:
     * |TITLE            |
     * |CALENDAR         |
     * |START            |
     * |END              |
     * |RECURRENT        |
     * |RECURRENCE       |
     * |INVITATION STATUS|
     */

    @Test
    public void verifyColumnNamesTest() {
        //we must add to every test at the beginning
        test = report.createTest("Verify column names");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        List<String> expected = Arrays.asList("TITLE", "CALENDAR", "START", "END", "RECURRENT", "RECURRENCE", "INVITATION STATUS");

        Assert.assertEquals(calendarEventsPage.getColumnNames(), expected);

        test.pass("Column names verified");
    }

//    public Object[] eve

    @Test(dataProvider = "calendarEvents")
    public void createCalendarEventTest(String title, String description) {
        //if you have more one test, and 1st pass but others failing,
        //you are getting session id is null exception
        //because driver object was not initialized in time
        //just create page objects inside a test
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        //only for extent report. To create a test in html report
        test = report.createTest("Create calendar event for " + title);
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.enterCalendarEventTitle(title);
        calendarEventsPage.enterCalendarEventDescription(description);
        calendarEventsPage.clickOnSaveAndClose();

        //verify that calendar event info is correct
        Assert.assertEquals(calendarEventsPage.getGeneralInfoDescriptionText(), description);
        Assert.assertEquals(calendarEventsPage.getGeneralInfoTitleText(), title);

        //for extent report. specify that test passed in report (if all assertions passed)
        test.pass("Calendar event was created successfully!");
    }

    @DataProvider
    public Object[][] calendarEvents() {
        return new Object[][]{
                {"Daily stand-up", "Scrum meeting to provide updates"},
                {"Sprint Review", "Scrum meeting where team discussing previous sprint"},
                {"Sprint Planning", "Scrum meeting where team discussing backlog for following sprint"}
        };
    }
}

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

testNG suit in our project : it is inside testng.xml file

       <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="Test suite">
<test name="Login">                                // this line providing what test you want to run
    <parameter name="reportName" value="login"/>    / this line providing what test you want to run
                                                    //this parameter is mean " we need to creat
                                                     different report based on different test name"
                                                     if test name is login ,the parameter value = "login"
                                                     if test name is calendarEvents then parameter should be "calendarEvents"
    <classes>
        <class name="com.automation.tests.vytrack.login.NewLoginTests"/> // this line is providing which class you want to test
    </classes>
</test>
<test name="Calendar events">
    <parameter name="reportName" value="calendarEvents"/>
    <classes>
        <class name="com.automation.tests.vytrack.activities.NewCalendarEventsTests"/> // anything after "java" folder , is your project path
    </classes>
</test>
</suite>




 TIP : IN above testNG suit schema , if we test " classes" it will immediately give us errors ,
 so we should type this : classes + TAP ---> it automatically bring us :

 <classes>

 </classes>   this formula.

 class is self closable tag, means  it will auto follow the sign "/" at the end of your route , just like this :

<class name="com.automation.tests.vytrack.login.NewLoginTests"/>

Just right click " testng.xml" file, then select " Run'/user/owners/idealproject"
 --> this is your project address in your computer
 if you dont have that "Run" button ,just close testng.xml file and open it again .

 this line of code explaination : <parameter name="reportName" value="calendarEvents"/>  is on the above .
  but how does testng know "reportName" ?  we just need to add

@Parameter("reportName") under " BeforeTest" tag in AbstractTestBase class.

smoke test suit formate example :

right click my project name on top of intelliJ ---> click file---> create new file ---> name is " smoke_test.xml"


<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="Smoke">
    <test name="Smoke test">
        <!--        represents extent report name-->
        <parameter name="reportName" value="smoke"/>
        <classes>
            <class name="com.automation.tests.vytrack.login.NewLoginTests">
                <methods>
                    <!--                    remove loginWithDDT method from test execution list-->
                    <exclude name="loginWithDDT"/>
                </methods>
            </class>
        </classes>
    </test>
</suite>



now lets explain smoke test steps :


if we wirte <methods></methods>  ---> this measn inside <methods>  ,
we put the tests that WE DON'T WANT TO DO SMOKE TEST ""  in thi case , we just remove loginWithDDT test out of smoke test suits,  in another words , we don't want to smoke test this "loginWithDDT" from the execution.

<methods></methods> should be inside the " <class></class>"

 */
