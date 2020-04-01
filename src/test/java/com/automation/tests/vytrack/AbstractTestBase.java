package com.automation.tests.vytrack;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public abstract class AbstractTestBase {
    //will be visible in the subclass , regardless on subclass location(same package or no)
    protected WebDriverWait wait;
    protected Actions actions;

    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReproter;
    protected ExtentTest test;

    @BeforeTest
    public void setupTest(){
        report = new ExtentReports();
        String reportPath = "";
        //location of report file
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            reportPath = System.getProperty("user.dir")+"\\test-output\\report.html";
        }else{
            reportPath = System.getProperty("user.dir")+"/test-output/report.html";
        }
        //is a HTML report itself
        htmlReproter = new ExtentHtmlReporter(reportPath);
        //add it to the reporter
        report.attachReporter(htmlReproter);
        htmlReproter.config().setReportName("VYTRACK Test Automation Result");
    }

     @AfterTest
    public void tearDownTest(){
        report.flush();//to release a report
    }

    @BeforeMethod
    public void setup(){
        String URL = ConfigurationReader.getProperty("qa1");
        Driver.getDriver().get(URL);
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(),15);
        actions = new Actions(Driver.getDriver());



    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult){
        //if test failed ,take a screenshot
       if(iTestResult.getStatus()==ITestResult.FAILURE){
           BrowserUtils.getScreenshot(iTestResult.getName());
       }
        Driver.closeDriver();
    }



}
