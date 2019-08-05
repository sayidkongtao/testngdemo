package com.sayid.testcases;

import org.testng.annotations.Test;

public class MyTest1Child1 extends MyTest1 {
    @Test
    public void testInChild1() {
        System.out.println("testInChild1: " + a);
    }
}
