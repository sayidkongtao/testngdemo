package com.sayid.testcases;

import com.sayid.listener.ScreenCapture;
import io.appium.java_client.AppiumDriver;

public class BaseTest {

    public AppiumDriver driver;

    public void setdriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public void takeScreen(String filename) {
        ScreenCapture.getScreen(driver, filename);
    }
}
