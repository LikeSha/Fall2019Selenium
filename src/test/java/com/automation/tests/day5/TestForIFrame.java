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
/*
frame - used to insert one HTML document on another one. There are 2 type of frames: frame and iframe.

<frame> - to divide page on sectors

<iframe> - to insert one page inside another one, anywhere.

iframes - usually used to display adds.

To be able to interact with the content inside a frame, you need to switch to it.

driver.switchTo().frame("nameOrId"); by id or name of the frame

driver.switchTo().frame(index);      by frame index, starting from 0

if there is only 1 frame = index will be 0
if there are 2 frames - first one will have index 0, second one index 1.

Counting - is from top to bottom.

  //if frame doesn't have a name or id,and index doesn't give accurate frame position, use webelement
        //for example below method :
        //WebElement frameElement = driver.findElement(By.className("someFrame"));
        //driver.switchTo().frame(frameElement);

usually, all frames have id or name;

driver.switchTo().defaultContent() - to exit from the frame. If you don't exit, selenium will not see the content outside of this frame.


 List<List<String>> -- nested frame

Windows:

First, go to the folder that contains the file you're interested in. The easiest way of copying a file path is to hold down the Shift key on your keyboard and then right-click on the file. (That displays extra functionality in the context menu). Then, choose "Copy as path" from the menu. This trick will also work on folders, if you want to copy the folder path. Even if the path doesn't contain any spaces, Windows will still automatically surround it with double-quotes.

Mac:
Right click on the file --> press and hold option button --> copy as a path name
To upload file in Selenium: you need to use sendKeys() method and provide path to the file.
 */