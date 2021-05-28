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
        // in this case ,find element using h4, h4 is a tag name, so if we want to check
        //if h4 is a unique element ,just input "h4" or " //h4" in the search box ( search box
        // appear when we use keyboard " ctrl + f " , search box is helping us to find
        //if the element is unique.

        // if the element is a combination of attribute + value format, for example : class="subheader"
        // then if we want to find out if this element is unique, we can only use css selector
        //to check it in the search box , we do this in search box : [class="subheader"]
        // pay attention : must have square bracket when using css selector. we simply put
        // attribure= value pair inside square bracket []

        if(expected.equals(actual)){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
        }


        // lets click on Logout button, It looks like a button, but it's actually a link
        //every element with <a> tag is a link
        //if you have couple spaces in the text, just use partialLinkText instead of linkText
        //partialLinkText, its like contains()--complete match doesn't required
        //linkText requires exact match ,partialLinkText allows you have spaces when testing
        //and fix itself
        //don't put space
        WebElement logout = driver.findElement(By.partialLinkText("Logout"));
        // WebElement logout = driver.findElement(By.linkText(" Logout"));
        // pay attention , linkText(" Logout")---there is a space in front of Logout
        // this case it worked ,we tested ,however, better to use partialLinkText.
        //in this case ,if you use partialLinkText ,you can just remove the space which
        //in front of " Logout"

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

