package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTestPractice {

    public static void main(String[] args) {
        //unit test
        //to check if our method works properly
        //if assertion fails, that means our method doesn't work correctly
        //that means we have to fix the method
        String expected = "cba";
        String toReverse = "abc";
        String actual = reverseString(toReverse);
        //Assertion
        verifyEquals(expected, actual);

    }
    //annotation
    //description --is not working for junit ,make sure that you use testng
     @Test(description = "Verify if method can reverse a string")
    public void test(){ // THIS IS UNIT TEST
       String expected ="elppa";
       String actual = reverseString("apple");
       //it coming from testng, junit also has this class
         //you can compare any data types here, Strings , primitives, arrays,objects
         // to verify if expected result is equals to actual
       Assert.assertEquals(actual,expected);
    }

    @Test(description = "Verify if method can reverse a string")
    public void test2(){
       String expected ="rac";
       String actual = reverseString("car");
       Assert.assertEquals(actual,expected);
    }



    public static boolean verifyEquals(String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("TEST PASSED");
            return true;
        } else {
            System.out.println("Test failed!!!");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            return false;
        }
    }

    /**
     * This method stands for reversing strings.
     *
     * @param str to reverse
     * @return reversed string
     */
    public static String reverseString(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
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