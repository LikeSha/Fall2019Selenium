package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class RadioButtons{

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/radio_buttons");
        driver.manage().window().maximize();

        BrowserUtils.wait(2);
        // printStackTrace(); method in utilities package ,
        // and this method will show whole class of thread
        // that involved, it shows you from beginning to the end where
        // errors and exceptions occured. this print called " StackTrace();
        //its a history of what entire exceptions occured and what
        //classes were involved this issue.
        //<input type="radio">
        List<WebElement> radioButtons = driver.findElements(By.tagName("input"));
        // List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));


        for(WebElement radioButton: radioButtons){

            //<input type="radio" id="red" name="color">
            String id = radioButton.getAttribute("id");

            // return true if the button already clicked
            boolean isSelected = radioButton.isSelected();
            System.out.println(id + " is selected ? " +isSelected);


            //if button is eligible to click
            //returns true of you can click on the button
            if(radioButton.isEnabled()) {

                radioButton.click();
                System.out.println("Clicked on :: "+id);
                BrowserUtils.wait(1);

            } else {
                System.out.println("Button is disabled, not clicked :: "+id);
            }
            System.out.println();

        }

        driver.quit();
    }
        }


/*
Today is March 7, 2020
    Agenda:
        radio buttons
        check boxes
        -- isDisplayed()
        -- isSelected()
        -- isEnabled()
        frames


        until we start testNG, we will develop test methods, then -----> we move onto unit testing frameworks,and we will develop independent test method for our test case , then ----> we will move to main method concept

##################################

WebElement element        = findElement
List<WebElement> elements = findElements

<input type="radio" >
Exception in thread "main" org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()? -- make sure that you don't use webdriver object after quit() method, it should be at the end of your test script.

.isEnabled()   - returns true, if button can be clicked, otherwise - false.
.isSelected()  - returns true, if button already clicked, otherwise - false.
.isDisplayed() - returns true, if element is visible. Applies to any kind of webelement, not only radio buttons.

NOTE: if element doesn't exists at all, you will get - NoSuchElementException.
This method will not work if element doesn't present in the HTML code.

To verify if element is not in HTML code at all, use findElements().size() == 0.
driver.findElements(By.by()).size() == 0;

<input type="radio" id="red" name="color">
 radioButton.getAttribute("id");    --> red
 radioButton.getAttribute("type");  --> radio
 radioButton.getAttribute("name");  --> color

 getText() ---> nothing

 <tag>Text</tag> // any text should be in between opening tag and closing tag ,in this case ( radio button case . no text , only attribute)

Priority:
1. element must be present
2. element must be visible
3. element must be enabled
 */






