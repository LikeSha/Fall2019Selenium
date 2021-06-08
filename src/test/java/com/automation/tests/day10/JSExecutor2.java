package com.automation.tests.day10;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor2 {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void verifyTitle(){
        String expected = "Practice";
        //we create javascriptexecutor object by casting webdriver object
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //executeScript ---this method executes javascript code
        //we provide js code as a String
        //return document.title--javascript code
        //document ---represents HTML page
        //.toString()--to avoid additional casting from Object to String
        String actual = js.executeScript("return document.title").toString();
        //if we don't call toString method , then we have to cast it , just like this :
        // String actual = (String) js.executeScript("return document.title");
        // if you want to get the title on webpage , right click "inspect " then at the page ,
        // open up tag " Console" --> type " document.title" . then the result would be "Practice" in red color

        // when we use Javascript code ? 1. to click(); 2.scroll; 3. to enter text

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void clickTest(){
        WebElement link = driver.findElement(By.linkText("Multiple Buttons"));
        // disable this click action, to perform it with js executor

//        link.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //after "" you can list webelements that will be used
        //as part of your javascript code
        //it's varargs,so you can list 0+   varargs = Variable Arguments, it works like array
        //arguments--listed after ,
        //use index to get specific webelement
        //WebElement arguments - {element,link, link2)
        //from left --to right
        js.executeScript("arguments[0].click()",link);
        //arguments[0].click()",link  0 because its a first webelement after comma

        WebElement button6 = driver.findElement(By.id("disappearing_button"));

        js.executeScript("arguments[0].click()",button6);
        //arguments[0].click()",link  0 because its a first webelement after comma

        BrowserUtils.wait(2);

        WebElement result = driver.findElement(By.id("result"));

        Assert.assertEquals(result.getText(),"Now it's gone!");
    }

    @Test
    public void textInputTest(){
            driver.findElement(By.linkText("Form Authentication")).click();
            BrowserUtils.wait(3);

            WebElement username = driver.findElement(By.name("username"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement loginbtn = driver.findElement(By.id("wooden_spoon"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            //to get text from input box --read attribute "value"
        //to enter text --set attribute "value"

        js.executeScript("arguments[0].setAttribute('value','tomsmith')" , username);
        js.executeScript("arguments[0].setAttribute('value','SuperSecretPassword')",password);
        js.executeScript("arguments[0].click()", loginbtn);

        BrowserUtils.wait(4);

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String subheader = js.executeScript("return document.getElementsByClassName('subheader')[0].textContent").toString();

        Assert.assertEquals(subheader,expected);

    }

    @Test
    public void scrollToElement(){
        BrowserUtils.wait(5);

        //href = link, URL
        WebElement link = driver.findElement(By.linkText("Cybertek School"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)",link);
        //why we put (true) ?
        //scroll until it’s true, become visible
        //GO TO developer.mozilla.org to find out more codding knowledge
    }
    @Test
    public void scrollTest(){

        driver.navigate().to("http://practice.cybertekschool.com/infinite_scroll");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 15; i++) {
            js.executeScript("window.scrollBy(0, 1000)");
            BrowserUtils.wait(1);
            // js.executeScript("window.scrollBy(0, 1000)"); this is default scroll down
            // if I put -1000 , it means scroll up
        }
    }



    @AfterMethod
    public void tearDown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

}
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