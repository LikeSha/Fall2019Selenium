package officeHours;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class BitrixFileUploading {

    @Test
    public void uploadFile(){
       WebDriver driver = Driver.getDriver();
       driver.get("https://login1.nextbasecrm.com/");
//login
        driver.findElement(By.name("USER_LOGIN")).sendKeys("helpdesk7@cybertekschool.com");
        driver.findElement(By.name("USER_PASSWORD")).sendKeys("UserUser", Keys.ENTER);

        BrowserUtils.wait(3);

        driver.findElement(By.id("microoPostFormLHE_blogPostForm")).click();

        BrowserUtils.wait(2);

        driver.findElement(By.id("bx-b-uploadfile-blogPostForm")).click();

        String path = System.getProperty("user.dir")+ "/VytrackTestUsers.xlsx";
        BrowserUtils.wait(2);
        driver.findElement(By.name("bxu_files[]")).sendKeys("path");
        BrowserUtils.wait(3);



       Driver.closeDriver();
    }
}
