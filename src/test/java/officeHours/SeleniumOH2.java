package officeHours;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumOH2 {

    /**
     * 1, Go to http://automationpractice.com
     * 2. Search for "tshirt" in a searchbox + click enter OR click search button
     * 3. Validate you got 'no result" message on UI
     * 4. Search for "t-shirt"
     * 5. Validate there was 1 result found
     * 6.add it to the cart

     */
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com");

        WebElement search_box = driver.findElement(By.id("search_query_top"));
        //WebElement--class in selenium/java and it has many useful methods
        //.sendKeys("value that we want to send--input)
        search_box.sendKeys("tshirt" + Keys.ENTER);
        // after running this code ,the result shows as below in inspect :
        /*
       <p class="alert alert-warning">
                No results were found for your search "tshirt" </p>

         */
        // so I decide do this :



        WebElement error = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
        String errorText = error.getText();
        //.getText()--> returns String(text) from the element
        System.out.println("Error message : " + errorText);
        //NoSuchElementException --it means we could not locate the element

        search_box.sendKeys("t-shirt" + Keys.ENTER);
        //StaleElementReferenceExeption---element is old/stale--we want to find
        //this element again OR refresh page

        Thread.sleep(5000);

        WebElement count = driver.findElement(By.className("product-count"));
        System.out.println("items found: " + count.getText());



        driver.quit();

    }


}
