package com.sayid.testcases;

import com.sayid.listener.ScreenCaptureListener;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({ScreenCaptureListener.class, com.sayid.listener.ExtentTestNGIReporterListener.class})
public class MyScreenTest extends BaseTest {

    private Logger logger = Logger.getLogger(MyScreenTest.class);

    @Test
    public void myScreenTest() throws MalformedURLException {
        logger.info("********************测试套件开始执行********************");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone");
        capabilities.setCapability("platformVersion", "13.1");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("udid", "17d28db11b1d8266059ec407dfda8dbda6f366c9");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("newCommandTimeout", 7200);
        capabilities.setCapability("startIWDP", true);
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        logger.info("驱动对象创建完毕");
        driver.get("http://wwww.baidu.com");
        int a = 1 / 0;
    }
}
