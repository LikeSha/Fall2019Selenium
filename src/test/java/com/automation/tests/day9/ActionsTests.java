package com.automation.tests.day9;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsTests {

    private WebDriver driver;
    private Actions actions;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        actions = new Actions(driver);
    }

    @Test
    public void hoverOnImage(){
       driver.get("http://practice.cybertekschool.com/hovers");
       driver.manage().window().maximize();
        BrowserUtils.wait(3);

        // to select image , if they have 3 images, when we inspect ,we input this
        // in the search bar : (//img)[1] , this is exclusively pointing at image 1
        // its just like array first item, pay attention to the parathesis surrounding
        //  //img   then following [], then input the number of image you want to select
        //   double forward slash " //" means the method you are using is xpath

        WebElement img1 = driver.findElement(By.xpath("(//img)[1]"));
        WebElement img2 = driver.findElement(By.xpath("(//img)[2]"));
        WebElement img3 = driver.findElement(By.xpath("(//img)[3]"));
        //build() is needed when you have couple of actions
        //always end with perform()
        //pause(1000)---like Thread.sleep(1000);
        // if you only have one item ( image) , you dont need build, but if you have multiple images
        // you need build to chain the actions.
        actions.moveToElement(img1).
                pause(1000).
                moveToElement(img2).
                pause(1000).
                moveToElement(img3).
                build().perform();

        // hover on the first image
        //verify that "name: user1" is displayed
        //hover over image to make text visible
        actions.moveToElement(img1).perform();
        WebElement imgText1 = driver.findElement(By.xpath("//h5[text()='name: user1']"));
        // (//h5)[1]<-- can also use this element to find out image1 text
        //verify that webelement that contains the text is visible
        Assert.assertTrue(imgText1.isDisplayed());

        BrowserUtils.wait(2);

        //move to the second image

        actions.moveToElement(img2).perform();
        WebElement imgText2 = driver.findElement(By.xpath("//h5[text()='name: user2']"));
        Assert.assertTrue(imgText2.isDisplayed());

    }

    /*
     to hover over each and every image , we use for each loop :

     List<WebElement> images = driver.findElements(By.tagName("img"));

       for(WebElement image : images){
           actions.moveToElement(image).pause(2000).perform();
     */

    @Test
    public void jqueryMenuTest(){
        //Task until 8:20
        driver.get("http://practice.cybertekschool.com/jqueryui/menu");
        //hover on "enabled"
        //hover on "downloads"
        //click on PDF
        driver.manage().window().maximize();
        BrowserUtils.wait(3);

        WebElement enabled = driver.findElement(By.id("ui-id-3"));
        WebElement downLoad = driver.findElement(By.id("ui-id-4"));
        WebElement pdf = driver.findElement(By.id("ui-id-5"));

        actions.moveToElement(enabled).
                pause(1000).
                moveToElement(downLoad).
                pause(1000).
                click(pdf).build().perform();





    }

     @Test
     public void dragAndDropTest(){
         driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
         driver.manage().window().maximize();
         BrowserUtils.wait(3);
         //click on accept cookies
         driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click();

         BrowserUtils.wait(3);
         WebElement earth = driver.findElement(By.id("droptarget"));
         WebElement moon = driver.findElement(By.id("draggable"));

         actions.dragAndDrop(moon, earth).perform();

         String expected = "You did great!";
         String actual = earth.getText();

         Assert.assertEquals(actual, expected);

     }


    @AfterMethod
    public void tearDown(){
        BrowserUtils.wait(3);
        driver.quit();
    }


}
/*
March 18,2020

     Agenda:
       Actions class
       JavascriptExecutor

       //moveToElement returns instance of action class that's why we can chain them

       //builder pattern => you put one method then you can take action
//builder pattern == chaining methods

//what is build?
//if you have multiple actions you have to put build
//to combine a couple of actions.

//build() is needed when you have couple of actions
//build combines the action; perform; starts the action
//in this example; first we move to one image then second so we used build
//always end with perform
//perform does not click, it starts the action, execute the event
//you can perform click, drag and drop etc
//actions class has different implementations
//moveToElement returns instance of action class that's why we can chain them


Why do we need JavaScriptExecutor?
In Selenium Webdriver, locators like XPath, CSS, etc. are used to identify and perform operations on a web page.
In case, these locators do not work you can use JavaScriptExecutor.
You can use JavaScriptExecutor to perform an desired operation on a web element.

//how to use javaScriptExecutor?
//javaScriptExecutor; it is an interface we can not create object out of it.
//But javascript executor and webDriver are like siblings
//So we will cast driver to JavascriptExecutor
//we convert webDriver object into JavaScriptExecutor
//JavascriptExecutor js = (JavascriptExecutor) driver;
//interface => they don't have implementation
//if you have interface as reference type you can see methods only coming from that interface
//you can not see other methods that are in other interfaces
//so we will use remoteWebDriver class as reference type :
//if you use remoteWebDriver class as reference type you do not need to cast anymore, it has everything
//like this => private RemoteWebDriver driver;
            //driver.executeScript("window.scrollBy(0, 250)");
//you need to cast if your reference type is webDriver; like this
=>  private WebDriver driver;
    //JavascriptExecutor js = (JavascriptExecutor) driver;

    Actions builder = new Actions(driver)
    builder.click(tableRows.get(1)).keyDown(Keys.CONTROL).click(tableRows.get(2).keyUp(Kyes.CONTROL)
    .build().perform();
 */