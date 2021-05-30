package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectByText {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);
        //create a webelement object for drop=down first
        WebElement simpleDropdown = driver.findElement(By.id("dropdown"));
        //provide webelement object into constructor
        Select selectSimpleDropdown = new Select(simpleDropdown);
        selectSimpleDropdown.selectByVisibleText(("Option 2"));// select by visible text
        BrowserUtils.wait(3);
        //and select option 1
        selectSimpleDropdown.selectByVisibleText("Option 1");

        //
        Select selectYear = new Select(driver.findElement((By.id("year"))));
        Select selectMonth = new Select(driver.findElement((By.id("month"))));
        Select selectDay = new Select(driver.findElement((By.id("day"))));

        selectYear.selectByVisibleText("2003");
        selectMonth.selectByVisibleText("February"); // pay attention to this part,
        // if select by value then in () put correspondence number ,january 1, February 2....
        //if selectByVisivleText must put text inside () ,text is BLACK COLOR
        selectDay.selectByVisibleText("22");


        //select all months one by one
        //getOptions() --returns all options from dropdown as List<WebElement>
        List<WebElement> months = selectMonth.getOptions();
        for(WebElement eachMonth : months){
            // get the month name and select based on that
            String monthName = eachMonth.getText();
            selectMonth.selectByVisibleText((monthName));
            // selectMonth.selectByVisibleText(eachMonth.getText());
            BrowserUtils.wait(1);

        }
        BrowserUtils.wait(5);

        Select stateSelect = new Select(driver.findElement(By.id("state")));
        stateSelect.selectByVisibleText("District Of Columbia");
        // option that is currently selected
        //getFirstSelectedOption() this method to use for verify that we selected what we already did.
        //in this case ,we selected District Of Columbia, now we need to verify we selected right thing.
        //getFirstSelectedOption()--returns a webelement thats why we need to call getText()
        //getText() retrieves visible text from the webelement
        String selected = stateSelect.getFirstSelectedOption().getText();
        if(selected.equals("District Of Columbia")){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
        }

        List<WebElement> states = stateSelect.getOptions();
        for(WebElement stateOption:states){
            System.out.println(stateOption.getText());
        }



        BrowserUtils.wait(3);
        driver.quit();


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





    }
}
