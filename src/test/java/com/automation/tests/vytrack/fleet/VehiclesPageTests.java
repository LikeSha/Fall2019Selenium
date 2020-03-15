package com.automation.tests.vytrack.fleet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VehiclesPageTests {

    /**
     * under fleet package create a class called VehiclesPageTests
     *
     *  in this class. you will need to create @beforemethod with setup and @aftermethod with teadown part.
     *  Use LoginPageTests class as a reference.
     *
     * create a test called verifyPageSubTitle
      -- in this test , you will need to navigate to Fleet ---->Vehicles and verify that page subtitleis equals to "All Cars"
     *
     *   user assertions for validation.
     */

    private WebDriver driver;
    private String URL = "https://qa2.vytrack.com/user/login";
    private String userName = "storemanager85";
    private String passWord = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy =By.id("prependedInput2");

    @Test
    public void verifyPageSubTitle(){

    }





    @BeforeMethod
         public void setup(){
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
             driver.get(URL);
             driver.manage().window().maximize();
    }

         @AfterMethod
         public void tearDown(){
             if(driver != null){
                 driver.quit();
                 driver= null;
             }
         }




}






