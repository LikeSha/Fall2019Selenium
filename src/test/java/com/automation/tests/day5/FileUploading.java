package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploading {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/upload");
        BrowserUtils.wait(5);

        WebElement upload = driver.findElement(By.id("file-upload"));

        //https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
        //I am gonna upload pom.xml file
        String filePath = System.getProperty("user.dir")+"/pom.xml";
        //System.getProperty() method it returns the location of your project. Where java program executes,
        //this "user.dir" (read as : user working directory) it returns location, its system property
        //user.dir in this case ,return this project which is " Fall2019Selenium " and pom.xml file
        // we can go to google to search " java system property"

        //it work only for my computer because only have this file
        //and my computer username is different than yours
        String file2Path = "/Users/studio2/Downloads/image (2).png";
        // in windows system, before you upload any file ,first hold " shift" key on your keyboard,
        //then right-click the file ---> select " copy as path" to upload file.

        //Windows:
        //
        //First, go to the folder that contains the file you're interested in.
        // The easiest way of copying a file path is to hold down the Shift key on your keyboard
        // and then right-click on the file. (That displays extra functionality in the context menu).
        // Then, choose "Copy as path" from the menu. This trick will also work on folders,
        // if you want to copy the folder path. Even if the path doesn't contain any spaces,
        // Windows will still automatically surround it with double-quotes.
        //
        //Mac:
        //Right click on the file --> press and hold option button --> copy as a path name
        //To upload file in Selenium: you need to use sendKeys() method and provide path to the file.

        System.out.println(filePath);//print path

        upload.sendKeys(filePath);

        driver.findElement(By.id("file-submit")).click();//click to upload


        BrowserUtils.wait(5);
        driver.quit();
    }

}