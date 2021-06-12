package com.automation.tests.day12;

import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DriverTest {

    @Test
    public void googleTest(){
        Driver.getDriver().get("http://google.com");
        Assert.assertEquals(Driver.getDriver().getTitle(),"Google");
        Driver.closeDriver();
    }
}
/*
March 24,2020

  Agenda:
        System properties
        .properties file
        Singleton driver
###############################
System properties - we use System class to get info about environment where java program is executing.
System Properties

In Properties, we examined the way an application can use Properties objects to maintain its configuration.
The Java platform itself uses a Properties object to maintain its own configuration. The System class maintains a Properties object that describes the configuration of the current working environment. System properties include information about the current user, the current version of the Java runtime, and the character used to separate components of a file path name.

The following table describes some of the most important system properties
Key Meaning
"file.separator"  Character that separates components of a file path. This is "/" on UNIX and "\" on Windows.
"java.class.path" Path used to find directories and JAR archives containing class files.
Elements of the class path are separated by a platform-specific character specified in the path.separator property.

"java.home"           Installation directory for Java Runtime Environment (JRE)
"java.vendor"     JRE vendor name
"java.vendor.url" JRE vendor URL
"java.version"        JRE version number
"line.separator"  Sequence used by operating system to separate lines in text files
"os.arch"         Operating system architecture
"os.name"         Operating system name
"os.version"      Operating system version
"path.separator"  Path separator character used in java.class.path
"user.dir"            User working directory
"user.home"           User home directory
"user.name"           User account name

just use System.getProperty("property_name") ---> property as a string
System.getProperties() ---> list of all properties
###################################################
.properties files are mainly used in Java programs to maintain project configuration data,
database config or project settings etc. Each parameter in properties file are stored as a pair of strings,
in key-value pair format, where each key is on one line.
You can easily read properties from some file using object of type Properties.
This is a utility provided by Java itself.

ConfigurationReader - to read project configuration from properties file:
    - URLs
    - browser type
    - credentials
    - data base connection strings
    - etc..
We load .properties file once, and use it everywhere in our project
it look like simple text file:
key=value
make sure that file has a .properties extension.
####################################################################
Design pattern - is a general reusable solution to a commonly occurring problem with a given context in software design.
Patterns are formalized best practices that programmer can use to solve some common software design problems
There are lots of design patterns and 3 categories:
Creational, Structural, Behavioral.
One of the most simplest creational design pattern is a Singleton.
Singleton - means single instance/object for entire project.
Rules:
    - private static instance
    - private constructor
    - public getter method, that returns instance already initialized.
WebdriverManger - setup browser driver (middleman between java code and browser)
DriverFactory - gives you every time new webdriver object;
1 webdrvier object = 1 browser
Driver class - gives you always same webdriver object. Every single test, will use same webdriver object.

 */