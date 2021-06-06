package com.automation.tests.practice;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationForm {

    private String URL = "http://practice.cybertekschool.com/registration_form";
    private WebDriver driver;
    // p tag name of success message
    // one xpath for all inputs: //label[text()='Label name']/..//input
    private By firstNameBy = By.name("firstname");
    private By lastNameBy = By.name("lastname");
    private By usernameBy = By.name("username");
    private By emailBy = By.name("email");
    private By passwordBy = By.name("password");
    private By phoneBy = By.name("phone");
    private By maleBy = By.cssSelector("input[value='male']");
    private By femaleBy = By.cssSelector("input[value='female']");
    private By otherBy = By.cssSelector("input[value='other']");

    private By dateOfBirthBy = By.name("birthday");
    private By departmentBy = By.name("department");
    private By jobTitleBy = By.name("job_title");

    // languages
    private By cplusplusBy = By.xpath("//label[text()='C++']/preceding-sibling::input");
    private By javaBy = By.xpath("//label[text()='Java']/preceding-sibling::input");
    private By javascriptBy = By.xpath("//label[text()='JavaScript']/preceding-sibling::input");
    // WE USE this xpaht preceding-sibling and following-sibling method to help us locate the dynamic element
    // this is very very useful method when locate dynamic element, we just need to find out the static element
    //then preceding-sibling or following-sibling to catch that element.
     // sign up button
    private By signUpBy = By.id("wooden_spoon");

    @Test
    public void test1(){

        driver.findElement(firstNameBy).sendKeys("Patrick");
        driver.findElement(lastNameBy).sendKeys("White");
        driver.findElement(usernameBy).sendKeys("testuser");
        driver.findElement(emailBy).sendKeys("test@email.com");
        driver.findElement(passwordBy).sendKeys("12345678");
        driver.findElement(phoneBy).sendKeys("234-123-1231");

        driver.findElement(maleBy).click();
        driver.findElement(dateOfBirthBy).sendKeys("01/01/1940");

        Select departmentSelect = new Select(driver.findElement(departmentBy));
        departmentSelect.selectByVisibleText("Department of Agriculture");

        Select jobTitleSelect = new Select(driver.findElement(jobTitleBy));
        jobTitleSelect.selectByVisibleText("SDET");

        driver.findElement(javaBy).click();
        driver.findElement(signUpBy).click();

        BrowserUtils.wait(5);

        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.tagName("p")).getText();

        Assert.assertEquals(actual,expected);

    }
     @Test
     public void verifyFirstNameLengthTest(){
        driver.findElement(firstNameBy).sendKeys("a");
        BrowserUtils.wait(3);
         WebElement warningMessage = driver.findElement(By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']"));
         Assert.assertTrue(warningMessage.isDisplayed());
    }

    @Test
    public void verityAlphabeticLettersOnlyTest(){
        driver.findElement(firstNameBy).sendKeys("123");
        //we are looking for specific text we care about visibility of this text after entering one character
        BrowserUtils.wait(3);
        WebElement warningMessage = driver.findElement(By.xpath("//small[text()='first name can only consist of alphabetical letters']"));
        Assert.assertTrue(warningMessage.isDisplayed());
    }


    @BeforeMethod
    public void setup(){
//        WebDriverManager.chromedriver().version("79").setup();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
/*
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