package com.automation.tests.day3;

import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementById {

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/login");
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        Thread.sleep(2000);

        // WebElement password is called " reference variable "
        // the " =" right side findElement() returns you webElement itself
        //in java ,you can use object without creating reference variable ,
        // so on the right side ,you have an object : driver.findElement(By.name("password"));
        // this is reference variable " WebElement password" to call that object from the memory
        // so when you want to use an object more than once , you need to create reference variable
        // or I can direct use object without crating reference variable , like :
        //driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword");

        driver.findElement(By.id("wooden_spoon")).click();



        Thread.sleep(2000);

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.tagName("h4")).getText();
        // you must put getText() method , so that it will return a String .

        if(expected.equals(actual)){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
        }


        // lets click on Logout button, It looks like a button, but it's actually a link
        //every element with <a> tag is a link
        //if you have couple spaces in the text, just use partialLinkText instead of linkText
        //partialLinkText---contains()--complete match doesn't required
        //linkText requires exact match ,partialLinkText allows you have spaces when testing
        //and fix itself
        //don't put space
        WebElement logout = driver.findElement(By.partialLinkText("Logout"));

        String href = logout.getAttribute("href"); // orange color is attribute in "inspect"
        String className = logout.getAttribute("class");

        System.out.println(href);
        System.out.println(className);

        logout.click();
        Thread.sleep(2000);

        //lets enter invalid credentials

        driver.findElement(By.name("username")).sendKeys("wrong");
        driver.findElement(By.name("password")).sendKeys("wrong");
        driver.findElement(By.id("wooden_spoon")).click();



        Thread.sleep(2000);

        WebElement errorMessage = driver.findElement(By.id("flash-messages"));

        System.out.println(errorMessage.getText());

        Thread.sleep(2000);


        driver.quit();



    }




    }

