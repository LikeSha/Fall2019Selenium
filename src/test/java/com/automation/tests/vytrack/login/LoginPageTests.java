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

       You have 500 test cases but the client aksed you to run only 40 of them for smoke test,
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
       //div[@class='example']//div==go to div element, that is any chld (grand child,
      grand grand child) of this element ( no limit for strpping down)

       CSS SELECTOR

 div[class='example'] > div ---go to div element, that is direct child of this element (only one step down) Greater (>) symbol.

 div[class=‘example’] div - go to div element, that is any child (grand child, grand grand child)
 of this element (no limit for stepping down). Just space.
 */