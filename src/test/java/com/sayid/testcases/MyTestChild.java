package com.sayid.testcases;

import org.testng.annotations.Test;

public class MyTestChild extends MyTest {

    @Test
    public void MyTestChild() {
        System.out.println("MyTestChild " + this.a);
    }
}
