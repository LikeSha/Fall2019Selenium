package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NoSelectDropdown {

    public static void main(String[] args) {

        //this one became a popular interview question

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");

        BrowserUtils.wait(3);
        driver.findElement(By.id("dropdownMenuLink")).click();//to expand dropdown

        //<a class="dropdown-item" href="https://www.amazon.com/">Amazon</a>
        BrowserUtils.wait(3);
//        driver.findElement(By.linkText("Amazon")).click();//click on option



        List<WebElement> allLinks = driver.findElements(By.className("dropdown-item"));
        for (WebElement link: allLinks){
            System.out.println(link.getText()+" : "+link.getAttribute("href"));
        }





        driver.findElement(By.linkText("Etsy")).click();//click on option

        BrowserUtils.wait(3);
        driver.quit();








        BrowserUtils.wait(3);
        driver.quit();


    }
}
/*
/*
From the selenium perspective, there are 2 types of drop-downs: select and other.

To handle select drop-downs, use Select class from selenium. You can select by:
- text
- value (<option value="smth">)
- index

In case of any other drop-down, just click on,wait until options becomes visible and click on that option.
You need a locator for dropdown and option.

to determine which is select drop-down which is not ,just observe inspection ,
if there is tag" select" in beginning , this is select drop-down



In Selenium, there is a class Select that is used to handle dropdowns.
It works only with <select> based drop-downs.
We can select by:
- text
- value
- index

In case of other drop-downs, just click on it and click on the option to select.
Don't use Select class if there is no <select> tag.

getFirstSelctedOption() - get selected option from the drop down

alert - javascript popup message that is used to notify user about something.
To handle it, use Alert methods in selenium:

driver.switchTo().alert().accept() - click ok
driver.switchTo().alert().dismiss() - click cancel
driver.switchTo().alert().sendKeys() - enter text
driver.switchTo().alert().getText() - read popup message

Xpath - the most powerful locator. There are 2 xpath's: absolute and relative

absolute starts with / and root element, for example: /html/body/div/
relative starts with // and any element, like //button or //input

Method that are available in Select class :

deselectAll()-----------Clear all selected entries

deselectByIndex(int index)--------------------Deselect the option at the given index

deselectByValue(java.lang.String.value)------------Deselect all options that have a value matching the argument

getAllSelectedOptions()

getFirstSelectedOption()

getOptions()

isMultile()

selectByIndex(int index)-------Select the option at the given index

selectByValue(java.lang.String value)----------------Select all options that have a value matching the argument

selectByVisibleText(java.lang.String text)-------Select all options that display text matching the argument

 */

