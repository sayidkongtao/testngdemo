package com.sayid.testcases;


import org.testng.annotations.BeforeSuite;

public class MyTest {

    public static int a;

    @BeforeSuite
    public void myTestDemo() {
        System.out.println("BeforeSuite in demo");
        a = 2;
    }
}
