package com.automation.tests.day12;

import com.automation.utilities.ConfigurationReader;
import org.testng.annotations.Test;

public class ConfigurationReaderTest {


    @Test
    public void readProperties(){
       String browser = ConfigurationReader.getProperty("browser");
        System.out.println(browser);
    }
}
