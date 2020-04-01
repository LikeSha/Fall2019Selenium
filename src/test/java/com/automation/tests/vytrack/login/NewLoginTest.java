package com.automation.tests.vytrack.login;

import com.automation.pages.LoginPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewLoginTest extends AbstractTestBase {

    /**
     * Login and verify that page title is a "Dashboard"
     * 10 minutes
     */
     @Test
    public void verifyPageTitle(){
         //test is coming from ExtentTest
         //we must add to every test at the beginning
         //test = report.createTest("Test name");
         test = report.createTest("Verify page title");
         LoginPage loginPage = new LoginPage();
         loginPage.login();
         Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
         //if assertion passed , it wil set test status in report to passed
         test.pass("Page title Dashboard was verified");

     }

    /**
     * Enter wrong credentials and verify warning message
     */
     @Test
    public void verifyWarningMessage(){
         LoginPage loginPage = new LoginPage();
         loginPage.login("wrong", "wrong");
         Assert.assertEquals(loginPage.getWarningMessageText(), "Invalid user name or password.");
         //take a screenshot
         BrowserUtils.getScreenshot("warning_message");
     }
}
