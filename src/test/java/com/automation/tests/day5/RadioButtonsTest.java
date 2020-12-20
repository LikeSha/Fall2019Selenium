package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RadioButtonsTest {

    public static void main(String[] args) {
//       If DriverFactory is not work for you ,use below two code
//        WebDriverManager.chromedriver().version("79").setup();
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/radio_buttons");
        BrowserUtils.wait(2);
        // <input type="radio" id="black" name="color">
        WebElement blackButton = driver.findElement(By.id("black"));
        // if we want to find yellow button ,we can use xPath  to do it :
        // //label[text()='Yellow']/preceding-sibling::input

        // if we want to find Green button,which is "disabled" ,we use xpath :
        // //label[contains(text(),'Green')].preceding-sibling::input

        //if visible and eligible to click  visible and eligible are two different things
        if(blackButton.isDisplayed() && blackButton.isEnabled()){
            System.out.println("CLICKED ON BLACK BUTTON");
            blackButton.click();
        }else{
            System.out.println("FAILED TO CLICK ON BLACK BUTTON");
        }

        BrowserUtils.wait(3);


        //how do we verify that button clicked
        //returns true, if button clicked
        if(blackButton.isSelected()){
            System.out.println("TEST PASSED!");
        }else{
            System.out.println("TEST FAILED");
        }

        driver.quit();

    }
}
