package com.automation.tests.day11;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExplicitWait {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
    }

    @Test
    public void waitForTitle(){
        driver.get("http://google.com");

        WebDriverWait wait = new WebDriverWait(driver,10);
        //wait up to 10 seconds until title contains Google
        wait.until(ExpectedConditions.titleContains("Google"));
        //ExpectedConditions.titleIs("Google")-- because Google webpage title is exact "Google"

        driver.navigate().to("http://amazon.com");
        wait.until(ExpectedConditions.titleContains("Amazon"));

    }

    @Test
    public void waitForVisibility(){
        driver.get("http://practice.cybertekschool.com/dynamic_loading/1");

        WebDriverWait wait = new WebDriverWait(driver,10);
        //click on start button
 //       driver.findElement(By.xpath("//button[text()='start']")).click();
        driver.findElement(By.tagName("button")).click();

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submitBtn =driver.findElement(By.cssSelector("button[type='submit']"));

        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys("tomsmith");
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("SuperSecretPassword");

        wait.until(ExpectedConditions.visibilityOf(submitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.className("subheader")).getText();

        Assert.assertEquals(actual,expected);


    }

    @Test
    public void elementToBeClickableTest(){

        driver.get("http://practice.cybertekschool.com/dynamic_loading/5");

        WebDriverWait wait = new WebDriverWait(driver,10);

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingoverlay")));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));

        username.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword");
        submitBtn.click();

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.className("subheader")).getText();

        Assert.assertEquals(actual,expected);



    }



    @AfterMethod
    public void tearDown(){
       driver.quit();
    }

}
/*
March 22,2020

Warm-up task for 15 minutes

Go to http://practice.cybertekschool.com/tables
Click on "Last name" column name
Verify that table is sorted by last name in alphabetic order.
until 11:19
"a".compareTo("b") = -1
61 - 62 = -1
a is before b
"a".compareTo("a")
61 - 61 = 0
0 means 2 strings are equals
table - tag that is used to create a web table in HTML
<table>
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>James</td>
            <td>May</td>
            <td>101 Groove St.</td>
        </tr>
        <tr>
            <td>Mark</td>
            <td>Portman</td>
            <td>974 Green Ave</td>
        </tr>
    </tbody>
</table>
//table[1] - get the first table on the page
//table[1]//tr - get all rows from first table
//table[1]//tbody//tr - get all rows from the first table, excluding table header
//table//tbody//td[1] - get first column
//table//tbody//tr[1] - get first row
//table//tbody//tr[2]//td[1] - get data from second row, first column
//table//th - get all column names
//table//tbody//td[last()] - get last column
//table//tbody//tr[last()] - get last row
break until 12:05

#############################################################################

Synchronization issues are very common in selenium webdriver. To overcome these issues we use waits.

As of now, we have used only one thing to slow down our script---Thread.sleep();

//DOM => treats html document as a tree. every element is node like h1, div..

Waits from selenium :

    --- implicit wait
    --- explicit wait
    --- fluent wait

 implicit wait ----apply only once at the beginning of test execution.
 It works before findElement method automatically.
 If webdriver cannot find an element in the DOM,
 it waits for defined amount of time for theelement to appear in the DOM.
 Once specified wait time is over, and element is not found, in specified time,
 it will throw NoSuchElementException

 Default implicit wait is ---0 second;

 When implicit wait has been applied , webdriver will try to look up for element in the specified period of time.
 If element was found faster, execution will be resumed.

 How to use implicit wait ?
 Use only once after get() method.

 driver.manage().timeouts().implicitlyWait(time, time units);

 for example : driver.manage().timeouts().implicitlyWait(20, Timeunits.SECONDS);

 That means webdriver , will be waiting for any element up to 20 seconds.

 #RECOMMENDATION : DO NOT use implicit and explicit waits together. You might get unexpectedly long waits.

 Explicit wait---provides better way to wait over implicit wait. Unlike implicit wait,
 you can write and use pre-defined conditions or custom conditions before proceeding further.

 Explicit wait ---exact condition and exact element.
 Implicit wait ----only one condition ( presence of element in the DOM) and it applies for every findElement()
 method invocation

 How to implement Explicit wait ?

 For this ,we use WebDriverWait class.

 WebDriverWait wait = new WebDriverWait(driver,time in seconds);

 Then, we have ExpectedConditions class that provides wait conditions.

ExpectedConditions class provides a set of predefined conditions to wait for before proceeding further.

some common explicit wait conditions :

elementToBeClickable();
visibilityOf();
presenceOfLocatedElement();
titleContains();

Example:

WebElement buttonElement = driver.findElement(By.xpath("//button[6]"));

WebDriverWait wait = new WebDriverWait(driver.10);
wait.until(ExpectedConditions.elementToBeClickable(buttonElement));

this wait means : wait for up to 10 seconds, until button becomes eligible to click

WebDriverWait has a default polling time---500 milliseconds.
It means that WebDriver is checking every 500 milliseconds if condition is true.

Explicit wait ---it's an individual approach. Wheres Implicit ---is general approach

Implicit wait ---we use only once
Explicit wait--as many times as we need

If condition failed , webdriver throws exception.

org.openqa.selenium.TimeoutException: Expected condition failed: waiting for title to contain “Amazon”.
Current title: “Google” (tried for 10 second(s) with 500 milliseconds interval)
When condition fails, it throws TimeoutException.


org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
Element <button type="submit" class="btn btn-primary">...</button> is not clickable at point (172, 480).
Other element would receive the click:
<div class="loadingoverlay" style="box-sizing: border-box; position: fixed; display: flex;
flex-flow: column nowrap; align-items: center; justify-content: space-around; background:
rgba(255, 255, 255, 0.8); top: 0px; left: 0px; width: 100%; height: 100%; z-index: 2147483647;
opacity: 1;">...</div>

just input in ctrl+F :  .loadingoverlay  then refresh page , then to see element ,but it disappear immediately

ElementClickInterceptedException --means that something else was sticked instead of your element

//overlay screen ==> div element goes on top of page we have this page this screen,
it will show up but not immediately there is a gap
//selenium starts manipulating once load done,
but there is a gap=> between loading complete - overlay screen pop up
//submit button condition not helpful cause it becomes true even before overlay appears
//so we put condition:
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingoverlay")));

break until 4:02

############Fluent wait ---it's a custom explicit wait.
With Fluent wait we can define the maximum amount of wait time for condition and element
as well as frequency with which to check for the condition (polling time)

For explicit wait, polling time is always 500 milliseconds,In Fluent wait , we can change it .


 */