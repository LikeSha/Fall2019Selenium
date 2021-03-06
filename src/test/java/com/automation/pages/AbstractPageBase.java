package com.automation.pages;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class will be extended by page classes
 * Any common webelements/locators can be stored here , in vytrack case ,those common webelements/locators
 * are on the top of "FLEET MANAGEMENT" PAGE, " Dashboards, Fleet Customers Sales Activities Marketing Report..
 * (these are tag name , under each of them , have module name, each tag has some module)
 * for example : under fleet manu we create VehiclesPage , under Activities manu we create CalendarEventsPage..
 * loginPage is different ,so it is separate with any of them ,because login page doesn't have
 * a lot of moveTo .. its simple
 * Since navigation menu doesn't belong to particular page
 * We cannot really create a dedicated page class to store
 * elements from that menu
 */

// object repository -- page package--> where is your page classes -->
// from page classes you create page objects. page objects created in the correspondence test class !
//In test classes, we just create a page objects and call methods to interact with page to perform testing.
// Also, we use assertions in the test classes.
public abstract class AbstractPageBase {
    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 25);

    @FindBy(css = "#user-menu > a")
    protected WebElement currentUser;

    public AbstractPageBase() {
        PageFactory.initElements(driver, this); // see AbstractLoginPage also have this PageFactory
        // PageFactory has to be initialized in every page class. Since this class is the parent class of all
        //other page classes,we can only do this once in this paticular class, we don't have to do this again in
        //each and every child page class
    }


    public String getCurrentUserName(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(currentUser));
        return currentUser.getText().trim();
    }


    /**
     * Method for vytrack navigation. Provide tab name and module name to navigate
     * @param tabName, like Dashboards, Fleet or Customers
     * @param moduleName, like Vehicles, Vehicles Odometer and Vehicles Costs
     */
    public void navigateTo(String tabName, String moduleName) {
//        String tabNameXpath = "//span[@class='title title-level-1' and contains(text(),'Fleet')]";
        //we replace 'Fleet' to below line of code '" + tabName + "' to make it dynamic
        String tabNameXpath = "//span[@class='title title-level-1' and contains(text(),'" + tabName + "')]";
        String moduleXpath = "//span[@class='title title-level-2' and text()='" + moduleName + "']";

        WebElement tabElement = driver.findElement(By.xpath(tabNameXpath));
        WebElement moduleElement = driver.findElement(By.xpath(moduleXpath));

        Actions actions = new Actions(driver);

        BrowserUtils.wait(20);

        actions.moveToElement(tabElement).
                pause(2000).
                click(moduleElement).
                build().perform();

        //increase this wait rime if still failing
        BrowserUtils.wait(20);
    }


}

