package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestForIFrame {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/iframe");
        BrowserUtils.wait(4);
        //before looking for that element ,we need to jump to that frame
        // you can specify : name, id, index or webelement of the frame
        //it's like we are jumping to another layer
        driver.switchTo().frame("mce_0_ifr");//driver.switchTo().frame("nameOrId"); by id or name of the frame
        //driver.switchTo().frame(index);      by frame index, starting from 0
        //if there is only 1 frame = index will be 0
        //if there are 2 frames - first one will have index 0, second one index 1.

        //if frame doesn't have a name or id,and index doesn't give accurate frame position, use webelement
        //for example below method :
        //WebElement frameElement = driver.findElement(By.className("someFrame"));
        //driver.switchTo().frame(frameElement);


        //now , this content will be visible
        WebElement textInput = driver.findElement(By.id("tinymce"));

        System.out.println(textInput.getText());

        BrowserUtils.wait(4);

        textInput.clear();// to delete text
        textInput.sendKeys("Hello, World!");

        BrowserUtils.wait(4);

        //exit from the frame
        driver.switchTo().defaultContent();

        WebElement heading = driver.findElement(By.tagName("h3"));
        System.out.println(heading.getText());


        driver.quit();


    }
}
