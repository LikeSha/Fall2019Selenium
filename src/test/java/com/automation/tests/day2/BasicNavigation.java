package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicNavigation {

    public static void main(String[] args) throws Exception {

        // to start selenium script we need :
        // setup webdriver(browser driver) and create webdriver object
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        // In selenium, everything starts from WebDriver interface
        // ChromeDriver extends RemoteWebDriver--> implements WebDriver
        driver.get("http://google.com");// to open a website

        Thread.sleep(3000);// for demo, wait 3 seconds
        // method that return page title
        // you can also see it as tab name, in the browser
        String title = driver.getTitle(); // returns <title> Some title</title> text
        String expectedTitle = "Google";

        System.out.println("Title is ...." + title);

        if(expectedTitle.equals(title)){
            System.out.println("Test passed!!");
        }else{
            System.out.println("test failed!!");
        }

        driver.close();// to close browser
        //browser cannot close itself



    }
}
