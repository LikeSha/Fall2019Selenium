package com.automation.tests.day7;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Xpath {
      // You can not create static variable inside main method ,because static belong to class
    // so you can only create static variable inside the class ( filed ) ( filed is the space between
    // class and main method
      public static String userNameLocator = "//label[text()='Username']/following-sibling::input";
      public static String passwordLocator = "//label[text()='Password']/following-sibling::input";
      public static String loginBtnLocator = "//button[contains(text(), 'Login')]";

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/login");
        BrowserUtils.wait(3);
        driver.findElement(By.xpath(userNameLocator)).sendKeys("tomsmith");
        driver.findElement(By.xpath(passwordLocator)).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath(loginBtnLocator)).click();




        BrowserUtils.wait(3);
        driver.quit();




    }
}
/*
March 10,2020

      Review locators:
              more xpath
              css Selector

 ###############################################################################

 Locators:
    id
    name
    tagName
    className
    linkText
    partialLinkText
    cssSelector
    xpath

In Selenium script, we create a webelement for every HTML element on the page.
We use tags and attributes to come up with a locator.

/*
    #1 id - if it's consistent, then use it

    <div id="smth"></idv>

    #2 name -  almost every time unique

    <div name="smth"></div>

    #3 className - almost never unique

    <div class="smth"></div>

    #4 tagName - almost never unique , except <h1>

    <h1>This is a page heading</h1>

    #5 linkText

         <a href="http://google.com">Link text</a>

    #6partialLinkText

          <a href="http://google.com">Link text</a>

          when you use it , you can mention only part of the link, not complete or exact

    #7 xpath - xml path

       xml - it's document that looks like HTML,also based on tags.
       there are 2 types of xpath : relative and absolute

       absolute , usually not used in test automation, for finding locators. In rear cases when it's useful :
       when you expect some elements in the specific place. You need to start from the root element. In case of HTML it's a -<html>
 */
/*
       forward slash - xpath always starts with it /
       /html/body/div[2]/button/

       you have to go from parent  to child
            html
                body
                    div
                    div
                       button

       node - it refers to any HTML element
       Instead, we mostly use relative xpath, and it starts with 2 forward slashes. //
       It can start from any element,not only from root element

       //tagName[@attribute='value']

       //tagName[@attribute"value"]

       //tagName[text()="text of element"]

     For example:

       <button onclick="button1()">Button 1</button>

       //button[@onclick='button1'()]

       //button[text()='Button 1']

       Text is always in between > <

       //button[.='Button 1']

       . - means any element

       //button[contains(text(), '1')]

       //button[contains(@onclick, 'button1')]

       ######## How to go from child to parent ?######################

       //button[text()='Button 1']/.. ( forward slash and two dots --- go back his parent)

       Go to the grandparent:

       //button[text()='Button 1']/../.. ( forward slash and two dots then slash two dots---
       go back his grand parent)
 */
/*
       Xpath advantages over cssSeelector : navigation in both directions: back and forward,ability
       to find element by text
 */
/*
       Go to any child :

       //div[@id='content']//button   -------------double forward slash

       Go to direct child only:

       //div[@id='content']/button    -------------single forward slash .
       one forward slash meas  one level down only.
 */

       //label[text()='First name']/following-sibling::div/input

/*

       /following-sibling::tagName - get the sibling that is located under the elemnt .

       Break until 8 pm

       <div>
          <input type="text" name="age">
          <label>Enter your age:</lable
       <div>

       input and label are siblings

       for input , label it's a following sibling
       for label, input it's a preceding sibling

       //input[@name='age']/following sibling::label

       //label[text()='Enter your age:']/preceding-sibling::input

       Let's say when input doesn't have id or unique name or something that can help us to find that element, we can find a label,that is next to it, and jump to that input

       To have both attributes

       //button[@type='submit' and @id='wooden_spoon']

       To have either one or another attribute

       //button[@type='submit' or @id='wooden_spoon']

       break until 9pm

         #8 CSS Selector

CSS (Cascading Style Sheets ) is a language that describes the style of an HTML document.
CSS describes how HTML elements should be displayed. Like the name implies it is a locator strategy by css. Native browser support is used by default, so please refer to w3c css selectors for a list of generally available css selectors. Beware that not all browsers were created equal, some css that might work in one version may not work in another.
 */
/*
Example of to find the cheese below:
<div id=“food">
<span class= >milk</span>
<span class= >cheese</span>
</div>
WebElement cheese = driver.findElement(By.cssSelector("#food span.dairy.aged”)); Or
WebElement cheese = driver.findElement(By.cssSelector(“span[class=‘dairy aged’]”));
in css:
. - class name
# - id

cssSelector                             xpath
tagName[attribute='value']      //tagName[@attribute='value']

###CONTAINS * star
    tagName[attribute*='value'] - contains
    button[id*='button']

####STARTS-WITH   ^ caret symbol
    tagName[attribute^='val']
    button[id^='button_']

####ENDS-WITH $ dollar
    tagName[attribute$='lue']
    button[id$='_button']

#NOTE: xpath doesn't support ends-with
~ tilde  ---> in termianl it pointing home directory, in selenium automation ,we don't use it

<button class="btn btn-primary" onclick="button6()" name="button6" id="disappearing_button">Don't click!</button>
    #disappearing_button | find by id

<a class="nav-link" href="/">Home</a>
    .nav-link            | find by class name

CssSelector: button[class="btn btn-primary"]:nth-of-type(2)
xpath:     //button[@class="btn btn-primary"][2]
:nth-of-type(n) - where n is an index
in xpath, we can use [index]

:first-child
:last-child

My interview questions, in terms of locators:
    what are the lcoators
    build a locator for web table
    relative vs absolute xpath
    which locator to use xpath or css and why
    how to handle dynamic element (xpath)
    unbalanced web table, how to handle
##################################
all about css selector: https://www.w3schools.com/cssref/css_selectors.asp
all about xpath: https://www.w3schools.com/xml/xpath_syntax.asp






Goal,                                 CSS 3,                   XPath

All                                   Elements,*,               //*
All                                   P Elements,p,             //p
All Child Elements,                   p>*,                      //p/*
Element By ID,                        #foo,                  //*[@id=’foo’]
Element By Class,                     .foo,"                 //*[contains(@class,’foo’)]"
Element With Attribute,               *[title],               //*[@title]
First Child of All P,                  p>*:first-child,          //p/*[0]
All P with an A child,                 Not possible,            //p[a]
Next Element,                          p + *,                  //p/following-sibling::*[0]
Previous Element,                     Not possible,             //p/preceding-sibling::*[0]

 */