package com.automation.tests.day4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FindElementsTest { // this topic is interview question StaleElementReferenceException

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/");

        Thread.sleep(3000);

        //how to collect all links from the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for(WebElement link:links){
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
            System.out.println();
//            link.click(); //click on link  // we stop these 3 line code because it throw StaleElementReferenceException
//            Thread.sleep(3000);
//            driver.navigate().back();
            //StaleElementReferenceException : stale element reference :
            // element is not attached to the page document
            //
            //Interview question : what is StaleElementReferenceException
            //
            //StaleElementReferenceException---means that selenium cannot find previously located element.
            // It happens , when you are trying to interact with element after page refresh or navigation
            //
            //How to handle it ?
            //
            //--if it occurs, you need to find element again
            //
            //try{
            //	//if it generaets exception once in a while
            //	//put it into try/catch and try to find element one more time
            //	driver.findElement(By.id("name")).click()
            //
            //}catch(StaleElementReferenceException e){
            //	driver.findElement(By.id("name")).click()
            //}
            //
            //what happens if element wasn't found, in case of findElement?
            //noSuchElementException
            //
            //what happens if no elements weren't found, in case of findElements?
            //nothing, you wilL get empty list
            //
            //Interview question : how to check if element doesn't exists any more/just doesn't exist ?
            //
//            if(driver.findElements(By.id("name").size()==0)){
//            	element doesn't exist!!
//            }
            //
            //You can use findElements method to find 0+ elements
            //
            //In case of findElement ---only 1 element .
            // if there is no element by given locator--NoSuchElementException
        }

        for (int i = 1; i <links.size() ; i++) {
            links.get(i).click();//driver.findElements(By.tagName("a")).get(i).click(); ---one shot code
//            Thread.sleep(2000);

            driver.navigate().back();
//            Thread.sleep(2000);
            //refresh list
            links = driver.findElements(By.tagName("a"));

        }

        driver.quit();
    }

}