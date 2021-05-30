package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Xpath {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        BrowserUtils.wait(3);
        //value can be inside '' or ""
        //if you don't know the tag name ,or want to skip tag name, use *
        //for example //*[@onclick='button()'] | * means any tag name
        WebElement btn1 = driver.findElement(By.xpath("//button[@onclick='button1()']"));
        btn1.click();

        WebElement result = driver.findElement(By.id("result"));
        System.out.println(result.getText());

        // click on button #2
        WebElement btn2 = driver.findElement(By.xpath("//button[text()='Button 2']"));
        btn2.click();
        System.out.println(result.getText());

        WebElement btn3 = driver.findElement(By.xpath("//button[starts-with(@id,'button_')]"));
        btn3.click();
        System.out.println(result.getText());

        WebElement btn4 = driver.findElement(By.xpath("//button[contains(@id,'_button')][1]"));
        // there are two _button, so we
        //have to add [1] followed contain part ,to specify we are reffering to the first element
        btn4.click();
        System.out.println(result.getText());

        WebElement btn5 = driver.findElement(By.xpath("//button[contains(text(),'5')]"));
        btn5.click();
        System.out.println(result.getText());

        // INDEXES START FROM 1 IN XPATH!!!!!!!!!






        BrowserUtils.wait(3);
        driver.quit();


    }
}
/*
Xpath - the most powerful locator. There are 2 xpath's: absolute and relative

absolute starts with / and root element, for example: /html/body/div/
relative starts with // and any element, like //button or //input

Example of absolute:

/html/body/div/div[2]/div/div/button[2]

never used in automation, because it's not reliable.
absolute xpath always starts with root element. In case of html document - it's html.

Also, absolute xpath starts with single front slash /

You cannot start from anywhere, you must start from the top, and go from child to parent without skipping.
Otherwise, locator will not work.

There are two types of XPath:
▪
For example: /html/body/div[1]/span
▪
Absolute XPath 
It is the direct way to find the element, but the disadvantage of the absolute XPath is that, if
there are any changes made in the path of the element then that XPath gets failed.
Relative XPath 
For Relative XPath, the path starts from the middle of the HTML DOM structure. It begins
with the double forward slash (//), which means it can search the element anywhere at the
webpage.
For example: //input[@id=‘btn’]

Remember:
/ - absolute
// - relative

it's not a good practice to copy xpath from the browser.

<button class="btn btn-primary" onclick="button4()" id="btnbtn_button">Button 4</button>


//button[@class='btn btn-primary'] or //*[@class='btn btn-primary']

//button[@onclick='button4()'] - find a button with onclick value button4()

//button[@id='btnbtn_button'] - find a button, with id btnbtn_button

//button[text()='Button 4'] - find a button, with text Button 4

//tag[@attribute='value']


//button[starts-with(@id,'button_')] - to find a button, where id starts with button_
so not exactly equals to button_, just starts with it.

*/

/*
//tag[starts-with(@attribute,'value')] -- find element, that has a following value in the beginning only.

//tag[starts-with(text(),'text')] -- find element, that has a following text in the beginning only.



//tag[contains(@attribute,'value')] -- find element, that partially contains value.
Doesn't matter in the beginning, end or in the middle.

//tag[contains(text(),'text')] -- find element, that partially contains text.
Doesn't matter in the beginning, end or in the middle.

If, there are couple elements with the same xpath, use index to specify needed one:

//button[index] or (//button)[index]

indexes starts with 1 in xpath


//button[contains(text(),'5')] - find a button, that contains 5 in the text

 */




