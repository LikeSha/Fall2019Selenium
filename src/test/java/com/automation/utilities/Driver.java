package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    //same for everyone
    private static WebDriver driver;

    //so no one can create object of Driver class
    //everyone should call static getter method instead
    private Driver() {

    }

    public static WebDriver getDriver() {
        //if webdriver object doesn't exist
        //create it
        if (driver == null) {
            //specify browser type in configuration.properties file
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
//                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver();//driver = new ChromeDriver(ChromeOptions);
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    /*
    public class Driver{ // for parellel testing driver class

        private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

        private Driver(){

        }

        public static WebDriver getDriver(String browser){

             if(driverThreadLocal.get() == null){
                 switch (browser) {
                     case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driverThreadLocal.set(new ChromeDriver());
                        break;
                      case "firefox":
                         WebDriverManager.firefoxdriver().setup();
                         driverThreadLocal.set(new FirefoxDriver());
                         break;
                      default:
                         throw new RuntimeException("Unimplemented driver type!");


                 }



             }

             return driverThreadLocal.get();


        }

        public synchronized static void closeDriver(){
            if(driverThreadLocal.get() ! =null){
               driverThreadLocal.get().quit();
               driverThreadLocal.remove();
            }
        }


    }
     */


}