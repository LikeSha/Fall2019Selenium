package com.automation.tests.day3;

import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public static void main(String[] args) throws Exception {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver =new ChromeDriver();

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/sign_up");
        WebElement fullName = driver.findElement(By.name("full_name"));
        fullName.sendKeys("Mister Twister");

        Thread.sleep(2000);

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("sdet@cybertek.com");

        Thread.sleep(2000);

        WebElement signUp =driver.findElement(By.name("wooden_spoon"));
        //when you see type="submit", you can use submit() instead of click()
        //it make sense to use when click() method doesn't work
        signUp.click();
        Thread.sleep(2000);

        //now we need to verify our test is a good test or not
        // so after click(); the page showing "Thank you for signing up.
        // Click the button below to return to the home page"
        // we take this sentence as a webelement and test it .
        //after typing [class="subheader"] we found className is unique ,so we can
        //use className this time as our locator By.className("subheader")
        // after typing h3 we found tag h3 also unique ( 1 of 1 in the search box)
        //so we can also h3 as tagName to find the element By.tagName("h3")
        // also can use By.name("signup_message") all above three are unique in this element

        String expected = "Thank you for signing up. Click the button below to return to the home page.";

        WebElement message = driver.findElement(By.className("subheader"));

        String actual = message.getText();//to get the text <h3>Text</h3>
        // getText() --returns visible text. Anything in between opening and closing tags is
        //a visible text

        if(expected.equals(actual)){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
        }


        driver.quit();//to close everything


    }
}
