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
            // this line of code : ("Column name: %s, position %s", actualColumnName, i) is equals to
            // ("Column name: " + actualColumnName + ", position " + i)
            // so the first symbol %s replace actualColumnName
            // the second symbol %s replace i
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
/*
/*
March 21,2020

Agenda :
   JavaScriptExecutor
   WebTables

   JavaScriptExecutor ---interface that allows to exectue JavaScript code as part of our selenium script

   JavaScript---used for front-end development , and supported by every browser and website.
   If, something doesn't work in selenium, we can do it with JavaScriptExecutor. For example : click

//once you find email cell in the first table that has this email (jdoe@hotmail.com) then go to following sibling has linkText delete :
////td[text()='jdoe@hotmail.com']//following-sibling::td/a[text()='delete']  TO MAKE IT EASIER CAN DO THIS WAY :
//table[1]//td[text()='fbach@yahoo.com']//..//a[text()='delete'] --GO BACK TO PARENT  OR GO TO SECND LINK :
//table[1]//td[text()='fbach@yahoo.com']//..//a[2] --THE SECOND LINK IS THE EMAIL YOU WANT TO DELETE

JavaScriptExecutor is an Interface

Since JavaScriptExecutor is an Interface , we cannot create an object of it. Instead, we can cast webdriver object.

JavascriptExectutor js = (JavascriptExecutor) driver;
Then , we can use executeScript method to run js code.

## This method , performs text input.

js.executeScript("arguments[0].setAttribute('value','tomsmith')",webelement);

##This method element returns page title as a String

js.executeScript("return document.title").toString();

###This function scrolls until webelement is visible

js.exectuteScript("arguments[0].scrollIntoView(true)",link);

##########################################################################

Web tables ---------used to store table data in HTML

Structure of web table

table
  thread
     tr
          th
          th
  tbody
     tr
          td
          td
     tr
          td
          td
  tfoot
     tr
          td

 table---begining of web table
 thead--table header, use to specify column names
 tbody--table body, main content
 tr==table row
 th--table header data
 td--table data

 use indexes, to get specific values.

 //table[1]//tbody//tr[1]//td[2]
    means:
       find first table
       get first row from table body
       and get second cell from that row

//table[1]//td[text()='fbach@yahoo.com']//following-sibling::td/a[text()='delete']

//table[1]//td[text()='fbach@yahoo.com']/..//a[text()='delete']

 */
