package com.automation.tests.day12;

import org.testng.annotations.Test;

public class SystemProperties {

    @Test
    public void test(){
       //System.getProperty("user.dir")+"/pom.xml"  <-- by giving this path to your teammates ,everyone get
        //same thing from you . it much easier for everyone.
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));

        //flexible path to downloads folder
        String pathToDownLoads = System.getProperty("user.home")+"/Downloads";

        System.out.println(pathToDownLoads);

        System.out.println(System.getProperty("os.arch"));
    }

}
