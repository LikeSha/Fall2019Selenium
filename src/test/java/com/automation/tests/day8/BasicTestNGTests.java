package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.*;

public class BasicTestNGTests {
    //runs only once before @BeforeClass and @BeforeMethod
    @BeforeTest
    public void beforeTest(){
        System.out.println("BEFORE TEST");
    }

    //Runs only once in the class after @AfterClass and @AfterMethod
    @AfterTest
    public void afterTest(){
        System.out.println("AFTER TEST");
    }

    // BeforeClass runs only once in the class before @Beforemethod and before any test
    //regardless on number of tests, it runs only once
    @BeforeClass
    public void beforeClass(){
        //something that should be done only once in the class before all tests
        System.out.println("BEFORE CLASS");
    }

    @AfterClass
    public void afterClass(){
        //something that should be done only once in the class after all tests
        System.out.println("AFTER CLASS");

    }


    //runs before every test automatically
    //works as a pre-condition or setup
    @BeforeMethod
    public void setup(){
        System.out.println("BEFORE METHOD");
    }
    //runs automatically after every test
    @AfterMethod
    public void teardown(){
        System.out.println("AFTER METHOD");
    }

// all @Test method NO NEED TO MAKE THEM STATIC! because ,the purpose to make method static is : we can
    // call those static methods out side of class, but the test method , we only run inside current class
    // so there is no point to make test method " static "
    @Test
    public void test1(){
        System.out.println("TEST 1");
        String expected = "apple";
        String actual = "apple";
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void test2(){
        System.out.println("TEST 2");
        int num1 = 5;
        int num2 = 10;
        //it calls hard assertion.
        //if assertion fails --it stops the execution (due to exception)
        Assert.assertTrue(num1 < num2);
    }

}
/*
March 14, 2020

Agenda :TestNG

Level of testing :

unit---testing of smallest functional piece of the application , for example  method or class ....

integration
system
UAT

In my project , developers were following this flow:

developers write a code -->unit tests to test code-->code review-->new build can be deployed to the test env
--->and here we do functional testing

who write unit tests ? For application code --developers.

Can I write some unit tests for my automation framework ? yes

import org.tesng.annotations.Test
@Test annotation used to crate a test. put it on top of the method.In this case ,
we don't use main method and we can create lots in one class.

 BEFORE SUITE

    BEFORE TEST

     BEFORE CLASS

         BEFORE METHOD
             TEST 1
         AFTER METHOD

          BEFORE METHOD
            TEST 2
          AFTER METHOD

      AFTER CLASS

   AFTER TEST

 AFTER SUITE

 SUITE ---it's a collection of tests. For example : regression suite.

 TestNG has 2 types of assertions : hard and soft

 Assertion class provides hard assertions

 What's the difference ? if hard assertion fails --test execution stops due to exception.
 In case of soft assertion,---test execution doesn't stop.
 For soft assertions, you can use the class---SoftAssert. But, common practice is to use only hard assertions.
  Junit, for example, has only hard assertions.

 We use annotation to create tests and organize them.

 Create a class called PracticeTests
 --setup before/after methods
   -- in before method. instantiate webdiver and naviate to http://practice.cybertekschool.com/
   -- in after method---just close webdriver

--create a test called loginTest
  --go to "Form Authentication" page
  --enter valid credentials
    username:tomsmith
    password:SuperSecretPassword
  --verify that following sub-header message is displayed "Welcome to the Secure Area.
  When you are don click logout below"

  ##############################################################################

  TestNG --it's a unit testing , functional testing, e2e testing tool.
  In test automation we use TestNG along with Selenium Webdriver to develop UI test automation scripts.
  TestNG was inspired by Junit (another very popular unit testing framework).
  TestNG has :

          -annotations for more complex test, like :@BeforeSuite, @BeforeClass, @BeforeMethod,@Test, etc.....
          -allows to create test suits with xml runners
          -has in-build HTML report
          -allows to group tests
          -allows to do Data Driven Testing


            // Interview question : HOW TO HANDLE SSL ISSUES IN SELENIUM ? THEN ANSWER IS BELOW  4 LINES

        //ChromeOptions--use to customize browser for tests

          ChromeOptions chromeOptions = new ChromeOptions();

       //   to ignore "Your connection is not private issue"

          chromeOptions.setAcceptInsecureCerts(true);
     //   provide chromeOptions object into chromedriver constructor
          driver = new ChromeDriver(chromeOptions);



           //if assertion fails ---it will throw exception and message will be printed
           //3 parameters : (expected ,actual ,message in case of error)

           Assert.assertEquals(actual,expected,"Sub-header message is not matching!"
           the sentence "Sub-header message is not matching!" is we added ,
           just in case assertion fails , we want to print some message out .

           NullPointerException means : you are trying to use object what wasn't instantiated !
           just like you are trying to drinking water but it doesn't exist.

 */