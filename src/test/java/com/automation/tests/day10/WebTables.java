package com.automation.tests.day10;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WebTables {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/tables");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
    }
     @AfterMethod
    public void tearDown(){
        BrowserUtils.wait(3);
        driver.quit();
     }
     @Test
    public void getColumnNames(){
        //th--represents table header
         List<String> expected = Arrays.asList("Last Name", "First Name", "Email", "Due", "Web Site", "Action");
         List<WebElement> columnNames = driver.findElements(By.xpath("//table[1]//th"));

         for(WebElement columnName :columnNames){
             System.out.println(columnName.getText());
         }
         Assert.assertEquals(BrowserUtils.getTextFromWebElements(columnNames),expected);
         //BrowserUtils.getTextFromWebElements(columnNames) ==>
// this method takes the text of every single webElement and puts it into collection of strings

     }
     @Test
    public void verifyRowCount(){
//           //tbody//tr--to get all rows from table body, excluding table header
           List<WebElement> rows = driver.findElements(By.xpath("//table[1]//tbody//tr"));
           //if we will get a size of this collection, it automatically equals to number of elements
         Assert.assertEquals(rows.size(),4);
     }

}
