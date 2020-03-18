package officeHours;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.HashMap;

public class VyTrackPractice {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://qa3.vytrack.com");
        WebElement username = driver.findElement(By.id("prependedInput"));
        username.sendKeys("salesmanager110");
        WebElement password = driver.findElement(By.id("prependedInput2"));
        password.sendKeys("UserUser123");
        password.submit();

        Thread.sleep(2000);
        //click on contacts
        //create contact
        //Use map to store inforamtion and use it later to enter in UI

        //   //tag[@attribute='value']

        WebElement contacts_link = driver.findElement(By.xpath("//span[.='Contacts']/following-sibling::a"));
        Thread.sleep(6000);
        contacts_link.click();
        Thread.sleep(4000);

        WebElement create_contact = driver.findElement(By.linkText("Create Contact"));
        create_contact.click();

        String currentTitle = driver.getTitle();
        if(currentTitle.equalsIgnoreCase("Create Contact - Contacts - Customers")){
            System.out.println("Title is expected");
        }else{
            System.out.println("Title is NOT expected");
        }

        HashMap <String,String> contact1 = new HashMap<>();
        contact1.put("First Name","John");
        contact1.put("Last Name","Smith");


        driver.quit();



    }
}
