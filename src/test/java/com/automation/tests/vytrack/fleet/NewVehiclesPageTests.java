package com.automation.tests.vytrack.fleet;

import com.automation.pages.LoginPage;
import com.automation.pages.fleet.VehiclesPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewVehiclesPageTests extends AbstractTestBase {

    @Test
    public void verifyTitle(){
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        loginPage.login();
        vehiclesPage.navigateTo("Fleet", "Vehicles");
        String expectedTitle = "All - Car - Entities - System - Car - Entities - System";
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}

/*
  Why we can use method : vehiclesPage.navigateTo("Fleet", "Vehicles"); because :
  VehiclesPage class extends AbstractPageBase class , the method navigateTo , is inside AbstractPageBase class
  since VehiclesPage extends that class, so it of course extends methods in that class.
 */