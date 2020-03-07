package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckBoxesTest {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/checkboxes");
        //TASK
        //verify that 1st checkbox is not selected and 2nd is selected

        BrowserUtils.wait(3);

      List<WebElement> checkBoxes = driver.findElements(By.tagName("input"));

      if(!checkBoxes.get(0).isSelected() && checkBoxes.get(1).isSelected()){
          System.out.println("TEST PASSED!");
      }else{
          System.out.println("TEST FAILED");
      }

      BrowserUtils.wait(2);
      //lets click on the first checkbox and verify its clicked
        // checkBoxes.get(0).click(0);
        WebElement checkbox1 =checkBoxes.get(0);// to get 1st checkbox
        checkbox1.click();

        if(checkbox1.isSelected()){
            System.out.println("TEST PASSED");
            System.out.println("checkbox #1 is selected");
        }else{
            System.out.println("TEST FAILED");
            System.out.println("checkbox #1 is not selected");
        }

      driver.quit();
//      if(checkBoxes.get(0).isDisplayed() && checkBoxes.get(0).isEnabled() && (!checkBoxes.get(0).isSelected()
//      && checkBoxes.get(1).isDisplayed() && checkBoxes.get(1).isEnabled() && checkBoxes.get(1).isSelected()){
//
//            System.out.println("TEST PASSED!");
//        }else{
//            System.out.println("TEST FAILED!");
//
//        }




    }

}
