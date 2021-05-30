package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class RegistrationForm {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/registration_form");
        BrowserUtils.wait(4);

        //enter first name
        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        driver.findElement(By.name("username")).sendKeys("jsmith");
        driver.findElement(By.name("email")).sendKeys("jsmith@email.com");
        driver.findElement(By.name("password")).sendKeys("supersecretpassword2020");
        driver.findElement(By.name("phone")).sendKeys("571-343-2342");

        List<WebElement> genders = driver.findElements(By.name("gender"));
        //select gender
        genders.get(0).click();// select male, for example

        driver.findElement(By.name("birthday")).sendKeys("01/01/2007");

        driver.findElement(By.id("inlineCheckbox2")).click();//check boxes select java

        BrowserUtils.wait(2);
        driver.findElement(By.id("wooden_spoon")).click(); //sign up button
        BrowserUtils.wait(2);

        //ADD VALIDATION PART





        driver.quit();
    }
}

/*
 Summary of today's topic :

 isDisplayed() --- to check if element is visible
returns false if element in the DOM, but hidden.
isEnabled()   --- to check if element is intractable (if you can do something with this element)
isSelected()  --- returns true, if radio button or check box is already clicked
frames - to insert another HTML document. Content inside a frame is not visible for selenium by default,
that's why you have to switch:
driver.switchTo().frame()
you can switch based on id/name, index, WebElement.
driver.switchTo().parentFrame() - if you are inside a child frame, switch to parent
driver.switchTo().defaultContent() - exit from all frames to original document.
to upload file, just use sendKeys() method and specify path to the file from your computer.
 */