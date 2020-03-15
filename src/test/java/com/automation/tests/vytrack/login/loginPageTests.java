package com.automation.tests.vytrack.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

//STATIC IMPORT OF ALL ASSERTIONS
import static org.testng.Assert.*;

public class loginPageTests {

    private WebDriver driver;
    //https is a secured version of http protocol
    //http-its hypertext transfer protocol that every single website is using now days
    // https--data encrypted ,no change for hackers to retrieve info
    //http-- data as a plain text, very easy to hack it
    private String URL = "https://qa2.vytrack.com/user/login";

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

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
