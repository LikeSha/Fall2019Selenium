package officeHours.office_hours4;

import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WaitPractice extends AbstractTestBase {

    protected WebDriver driver;

    @BeforeMethod
    public void setUpDriver(){
        driver = Driver.getDriver();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    /**
     * http://qa3.vytrack.com
     * salesmanager110
     * UserUser123
     *
     * /**
     * Implicit wait--wet it 1 time and it will work for every findeElement method
     * -NoSuchElementException
     *
     * Thread.sleep -- not Selenium wait! Thread--java class, sleep() method of Thread calss stops the
     * execution of java program
     * -We never want to use this method in our tests
     *
     * Explicit wait --we have to declare every time before the iteration with element
     * Expected Condition we are looking for
     *
     * Singleton ---it helps us to make sure we hae only 1 instance of driver object at a time
     */


     @Test
    public void testWait(){

         driver.get("http://qa3.vytrack.com");
         WebElement user = driver.findElement(By.id("prependedInput"));
         WebElement password = driver.findElement(By.id("prependedInput2"));
         WebElement submit = driver.findElement(By.id("_submit"));
         user.sendKeys("salesmanager110");
         password.sendKeys("UserUser123");
         submit.click();




         // expected condition : https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html

     }

}
