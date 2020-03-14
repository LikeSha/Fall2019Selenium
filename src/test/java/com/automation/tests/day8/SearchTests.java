package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests {

    private WebDriver driver;

    @Test
    public void googleSearchTest(){
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("java", Keys.ENTER);
        BrowserUtils.wait(2);
        //since very search item has a tag name <h3>
        //it's the easiest way to collect all of them
        List<WebElement> searchItems = driver.findElements(By.tagName("h3"));
        for(WebElement searchItem : searchItems){
            String var = searchItem.getText();
            //if there is a text --print it
            if(!var.isEmpty()){
                System.out.println(var);
                //verify that every search result contains java
                //is some of the search results
                //doesn't contain java word, it will fail the test
                Assert.assertTrue(var.toLowerCase().contains("java"));
                //test without assertion is useless - what makes test => test
                //without assertion you can not understand test has passed or failed
                System.out.println(var.toLowerCase());
                System.out.println();
            }

        }
    }

    /**
     * Given user is on the amazon.com page
     * when user enters "Java" as a search item
     * then user clicks on the search button
     * and user clicks on the first search item
     * and user verifies that title of the search item contains "Java"
     */
    @Test(description = "Search for Java book on amazon")
                public void amazonSearchTest(){
                    driver.get("http://amazon.com");

                }



    @BeforeMethod
    public void setup(){
        //setup webdriver
        WebDriverManager.chromedriver().version("97").setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown(){
        //close browser and destroy webdriver object
        driver.quit();
    }




}
