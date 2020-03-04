package com.automation.tests.day3;

import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementsPractice {

//    /**
//     * //we are writing same codes again and again
//     * //we will write a method and call it : DRY Principle
//     * //sth that support our code : utilities
//     * //under automation package
//     * //We created utilities package and DriverFactory class under automation package
//     * //we wrote reusable methods inside DriverFactory class named => createDriver
//     * //whenever we need to crate webDriver object
//     * //we will call our custom method
//     * /*
//     * WebDriverManager.chromedriver().setup();
//     * WebDriver driver = new ChromeDriver();
//     *  */
//     *
//     */
    public static void main(String[] args) {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver =new ChromeDriver();

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/sign_up");


        driver.quit();//to close everything


    }
}
