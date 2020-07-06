package com.automation.tests.day10;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setHeadless(true);//to run browser without GUI . Makes browser invisible.
//        driver= new ChromeDriver(chromeOptions);
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
         //expected --4 rows in the table
         Assert.assertEquals(rows.size(),4);
     }

    /**
     * To get specific column, skip row index, and just provide td index
     */
    @Test
    public void getSpecificColumn(){
        // td[5] ---column with links
             List<WebElement> links = driver.findElements(By.xpath("//table[1]//tbody//tr//td[5]"));
        System.out.println(BrowserUtils.getTextFromWebElements(links));

      }
    /**
     * Go to tables example page
     * Delete record with jsmith@gmail.com email
     * verify that number of rows is equals to 3
     * verify that jsmith@gmail.com doesn't exists any more
     */

      @Test
    public void deleteRowTest(){

          String xpath = "//table[1]//td[text()='jsmith@gmail.com']/..//a[text()='delete']";
          // /..//a[text()='delete']";  the /.. means go back to parent
          //or do this way ://table[1]//td[text()='jdoe@hotmail.com']/following-sibling::td/a[text()='delete']
          // or do this : //table[1]//td[text()='jdoe@hotmail.com']/..//a[2]<--second link is delete ,first is edit
          driver.findElement(By.xpath(xpath)).click();
          BrowserUtils.wait(3);
          //get count of rows
          int rowCount = driver.findElements(By.xpath("//table[1]//tbody//tr")).size();
          Assert.assertEquals(rowCount, 3);

          List<WebElement> emails = driver.findElements(By.xpath("//table[1]//td[text()='jsmith@gmail.com']"));

          Assert.assertTrue(emails.isEmpty());


      }

    /**
     * Let's write a function that will return column index based on column name
      */
    @Test
         public void getColumnIndexByName(){

        String columnName = "Email";

        List<WebElement> columnNames = driver.findElements(By.xpath("//table[1]//th"));

        int index = 0;

        for (int i = 0; i < columnNames.size(); i++) {
            String actualColumnName = columnNames.get(i).getText();
            System.out.println(String.format("Column name: %s, position %s", actualColumnName, i));
            if (actualColumnName.equals(columnName)) {
                index = i + 1;
                break;
            }
        }
        Assert.assertEquals(index, 3);


         }
          @Test
         public void getSpecificCell(){
              String expected = "http://www.jdoe.com" ;
              int row = 3;
              int column = 5;
              String xpath = "//table[1]//tbody//tr[" +row+ "]//td[" +column + "]";

              WebElement cell = driver.findElement(By.xpath(xpath));

              Assert.assertEquals(cell.getText(),expected);
         }


}
