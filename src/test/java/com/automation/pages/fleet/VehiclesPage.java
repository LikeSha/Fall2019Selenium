package com.automation.pages.fleet;

import com.automation.pages.AbstractPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehiclesPage extends AbstractPageBase {

    @FindBy(partialLinkText = "Create Car")
    private WebElement createCar;

    public void clickToCreateCar(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(createCar)).click();
    }

    // we extends AbstractPageBase ,what it give us ? First , PageFactory initialized ! then navigations available,
    // it inherited all properties and behavior of AbstractPageBase class.

// why do we need this method in this page ? why we not directly made this webelement available in the test class and
    //click on it ? the reason is very simple : because of wait issue. we need to take care of this wait issue
    // in this page class ,because this vytrack application has a lot of problems. we can provide additional
    // wait to support finding webelement.
}

/*
  page to page : page--page  ---> VehiclesPage extends AbstractPageBase
  test to test : test --test ---> NewLoginTest extends AbstractTestBase
 */