package com.automation.tests.day11;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void fluentWaitTest() {
        driver.get("http://practice.cybertekschool.com/dynamic_loading/6");
        Wait<WebDriver> wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(5)).
                ignoring(NoSuchElementException.class);
        BrowserUtils.wait(5);
//        WebElement loadingoverlay = driver.findElement(By.className("loadingoverlay"));
//
//        wait.until(ExpectedConditions.invisibilityOf(loadingoverlay));
//        WebElement submitBtn = wait.until(new Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver webDriver) {
//                return driver.findElement(By.xpath("button[text()='Submit']"));
//            }
//        });
        WebElement submitBtn = wait.until(driver -> driver.findElement(By.xpath("//button[text()='Submit']")));
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        submitBtn.click();
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
 If element was found faster, exictuion will be resumed.

 How to use implicit wait ?
 Use only once after get() method.

 driver.manage().timeouts().implicitlyWait(time, time units);

 for example : driver.manage().timeouts().implicitlyWait(20, Timeunits.SECONDS);

 That means webdriver , will be waiting for any element up to 20 seconds.

 #RECOMMENDATION : DO NOT use implicit and explicit waits together. You might get unexpectedly long waits.

 Explicit wait---provides better way to wait over implicit wait. Unlike implicit wait,
 you can write and use pre-defined conditions or custom conditions before proceeding further.

 Exlicit wait ---exact condition and exact element.
 Implicit wait ----only one conditon ( presence of element in the DOM) and it applies for every findElement()
 method invocation

 How to implement Explicit wait ?

 For this ,we use WebDriverWait class.

 WebDriverWait wait = new WebDriverWait(driver,time in seconds);

 Then, we have ExpectedConditions class that provides wait conditions.

ExpectedConditions class provides a set of predefined conditions to wait for before proceeding further.

somoe common explicit wait conditions :

elementToBeClickable();
visibilityOf();
presenceOfLocatedElement();
titleContains();

Example:

WebElement buttonElement = driver.findElement(By.xpath("//button[6]"));

WebDriverWait wait = new WebDriverWait(driver.10);
wait.until(ExpectedConditions.elementToBeClickable(buttonElement));

this wiat means : wait for up to 10 seconds, until button becomes eligible to click

WebDriverWait has a dfault polling time---500 milliseconds.
It means that WebDriver is checking every 500 milliseconds if conditon is true.

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