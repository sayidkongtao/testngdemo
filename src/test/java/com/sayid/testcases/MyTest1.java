package com.sayid.testcases;

import org.testng.annotations.BeforeSuite;

public class MyTest1 {

    public static int a;

    @BeforeSuite
    public void myTestDemo1() {
        System.out.println("BeforeSuite in demo1");
        a = 4;
    }
}
