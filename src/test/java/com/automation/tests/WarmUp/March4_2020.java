package com.automation.tests.WarmUp;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class March4_2020 {

    static WebDriver driver;

    public static void main(String[] args) {
        ebayTest();
        amazonTest();
//        wikiTest();
    }

    /**
     * Go to ebay --->          driver.get("http://ebay.com");
     * enter search term        input.sendKeys("java book");
     * click on search button   searchButton.click();
     * print number of results
     */
    public static void ebayTest(){
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://ebay.com");
        driver.findElement(By.id("gh-ac")).sendKeys("java book");
        driver.findElement(By.id("gh-btn")).click();
        WebElement searchResults = driver.findElement(By.tagName("h1"));
        System.out.println(searchResults.getText().split(" ")[0]);
        driver.quit();
    }

    /**
     * go to amazon
     * enter search term
     * click on search button
     * verify title contains search term
     */
    public static void amazonTest(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://amazon.com");
        //enter text and click ENTER
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java book", Keys.ENTER);
        String title = driver.getTitle();
        if(title.contains("java book")){
            System.out.println("TEST PASSED");
        }else {
            System.out.println("TEST FAILED");
        }
        driver.quit();
    }

    /**
     * Go to wikipedia.org
     * enter search term `selenium webdriver`
     * click on search button
     * click on search result `Selenium (software)`
     * verify url ends with `Selenium_(software)`
     */
    public static void wikiTest(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.findElement(By.id("searchInput")).sendKeys("Selenium(software)",Keys.ENTER);




        driver.quit();
    }
}
