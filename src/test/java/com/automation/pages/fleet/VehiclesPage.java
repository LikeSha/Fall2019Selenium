package com.automation.pages.fleet;

import com.automation.pages.AbstractPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehiclesPage extends AbstractPageBase {
    // we extends AbstractPageBase ,what it give us ? First , PageFactory initialized ! then navigations available,
    // it inherited all attributes and behavior of AbstractPageBase class.
    @FindBy(partialLinkText = "Create Car")
    private WebElement createCar;

    public void clickToCreateCar(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(createCar)).click();
    }

}

/*
  page to page : page--page  ---> VehiclesPage extends AbstractPageBase
  test to test : test --test ---> NewLoginTest extends AbstractTestBase
 */