package com.automation.tests.vytrack.login;

import com.automation.pages.LoginPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewLoginTests extends AbstractTestBase {

    /**
     * Login and verify that page title is a "Dashboard"
     * 10 minutes
     */
     @Test(groups = "smoke")
    public void verifyPageTitle(){
         //test is coming from ExtentTest
         //we must add to every test at the beginning
         //test = report.createTest("Test name");
         test = report.createTest("Verify page title");
         LoginPage loginPage = new LoginPage();
         loginPage.login();
         test.info("Login as store manager");//log some steps
         Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
         //if assertion passed , it wil set test status in report to passed
         test.pass("Page title Dashboard was verified");

     }

    /**
     * Enter wrong credentials and verify warning message
     */
     @Test
    public void verifyWarningMessage(){
         test = report.createTest("Verify warning message");

         LoginPage loginPage = new LoginPage();
         loginPage.login("wrong", "wrong");
         Assert.assertEquals(loginPage.getWarningMessageText(), "Invalid user name or password.");
         //take a screenshot
         BrowserUtils.getScreenshot("warning_message");

         test.pass("Warning message is displayed");
     }

     @Test(dataProvider = "credentials")
    public void loginWithDDT(String userName, String password) {
        test = report.createTest("Verify page title" + userName);
        LoginPage loginPage = new LoginPage();
        loginPage.login(userName, password);
        test.info("Login as " + userName);//log some steps
        BrowserUtils.wait(2);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
        test.pass("Page title Dashboard was verified");
    }
     //Object[][] or Object[] or Iterator<Object[]>
     //Object[] - 1 column with a data
     //Object[][] 2+

    @DataProvider
    public Object[][] credentials(){
         return new Object[][]{
                 {"storemanager85","UserUser123"},
                 {"salesmanager110", "UserUser123"},
                 {"user16",          "UserUser123"}
         };
      }

}
