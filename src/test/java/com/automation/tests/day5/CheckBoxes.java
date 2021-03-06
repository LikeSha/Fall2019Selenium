package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckBoxes {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/checkboxes");
        BrowserUtils.wait(5);
        //<input type="checkbox" checked="">
        List<WebElement> checkBoxes = driver.findElements(By.tagName("input"));
        // use cssSelector :   List<WebElement> checkBoxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        // use xpath :   List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        /*
        using for each loop to loop each and every checkbox :

        for(WebElement checkbox : checkboxes){
            Thread.sleep(2000); ( dong forget put throws Exception after main method)
            if(!checkbox.isSelected()){
               checkbox.click();
            }



        }
        driver.quit();
         */

//        checkBoxes.get(0).click(); // click on first checkbox

        BrowserUtils.wait(2);

        //go over collection of checkboxes
        for(int i=0; i<checkBoxes.size(); i++) {
            //       if visible,                            eligible to click  and         not clicked yet
            if (checkBoxes.get(i).isDisplayed() && checkBoxes.get(i).isEnabled() && (!checkBoxes.get(i).isSelected())) {
                //if checkbox is not selected, click on it
                checkBoxes.get(i).click(); // click on the checkbox
                System.out.println(i+1 +" checkbox clicked!");
            } else{
                System.out.println(i+1 +" checkbox wasn't clicked!");
            }
        }
        BrowserUtils.wait(2);
        driver.quit();
    }


    }

/*
checkboxes note : if we see 2 checkboxes, first we need to find out if checkbox unique in the html page,
how to check out if it is unique one ? (ctrl+f)--> we type cssSelector syntax : input[type='checkbox'],
and the page shows there are 1 of 2, means there are 2 checkboxes using same attribute " type ",
suppose we need to select the first one , the path would be like this :

input[type='checkbox']:nth-oftype(1) ---> and we use this path write our code in IntelliJ,
for example, we can write :
driver.findElement(By.cssSelector("input[type='checkbox']:nth-oftype(1)")).click();


 */