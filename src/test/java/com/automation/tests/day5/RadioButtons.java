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









