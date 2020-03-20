package officeHours;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatusCode {

    WebDriver driver = DriverFactory.createDriver("chrome");

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
    }


    @Test(priority = 100, description = "Verify that the following message is displayed: 'This page returned a 500 status code'")
    public void testCase12() throws InterruptedException {
        //Test Case #12
          /*
              Step 1. Go to "https://practice-cybertekschool.herokuapp.com"
             Step 2. And click on "Status Codes".
             Step 3. Then click on "500".
             Step 4. Verify that following message is displayed: "This page returned a 500 status code"
          */

        //Step 1

        driver.get("https://practice-cybertekschool.herokuapp.com");

        //Step 2
        /*
           xpath :
           //a[text()='Status Codes']
          //a[contains(text(),'Status Codes')]
          //ul/li[46]
          //a[@href="/status_codes"]
         linkText :
             lintText("Status Codes")
             partialLinkText:
             partialLintText("Status Codes")
             partiallinkText("Status")
             partiallinkText("Codes")

          CssSelector
             [href="/status_codes"]
              */

        WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
        statusCodeLink.click();
        Thread.sleep(3000);


        //Step 3
        WebElement statusCode = driver.findElement(By.linkText("500"));
        statusCode.click();

        Thread.sleep(3000);

        // Step 4
        String expectedMessage = "This page returned a 500 status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();

        Thread.sleep(3000);

        //System.out.println(actualMessage);
        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Passed");
        } else {
            System.out.println("FAILED");
        }

    }
    /*

       Step 1. Go to "https://practice-cybertekschool.herokuapp.com"
       Step 3. And click on "Status Codes".
       Step 4. Then click on "404".
       Step 5. Verify that following message is displayed: "This page returned a 404 status code"
       */


    @Test(priority = 1, description = "Verify that the following message is displayed: 'This page returned a 404 status code'")
    public void testCase11() throws InterruptedException {

        // step 1
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");

        //step 2
        WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
        statusCodeLink.click();

        Thread.sleep(3000);

        //step 3
        WebElement statusCode = driver.findElement(By.linkText("404"));
        statusCode.click();

        Thread.sleep(3000);

        //step 4
        String expectedMessage = "This page returned a 404 status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();

        Thread.sleep(3000);

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Passed");
        } else {
            System.out.println("FAILED");
        }


    }

    /**
     * DataProvider--> Provides data to test cases
     */

    @DataProvider(name = "testData")
    public static Object [] testData(){
        return new Object [] {"404","500","301","200"};
    }

    /**
     * DataProvider returns data in form of single dimensional Object array (Object [] or 2
     * dimensional object array (Object [][]
     *
     * object [] --->when you have only 1 parameter
     * object [] [] ---> when you have 2+ parameters
     * cannot carry
     * @param code
     */



    @Test(dataProvider = "testData")
    public void statusCodes(String code){
        //500, 404 should be the parameters
        //Step 1
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        //Step 2
        WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
        statusCodeLink.click();
        //Step 3
        WebElement statusCode = driver.findElement(By.linkText(code));
        statusCode.click();
        String expectedMessage ="This page returned a "+code+" status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();
//        if(actualMessage.contains(expectedMessage)){
//            System.out.println("Passed");
//        }else{
//            System.out.println("FAILED");
//        }
        /**
         * The following is a HARD assertion and contains a message that is desplayed only if the assertion fails
         *
         * when a HARD assertion fails the rest of the script is skipped
         */

        Assert.assertTrue(actualMessage.contains(expectedMessage),"The status code does not exist");

        driver.close();
    }


    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
