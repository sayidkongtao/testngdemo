package com.sayid.listener;

import com.sayid.testcases.BaseTest;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenCaptureListener extends TestListenerAdapter {
    private Logger logger = Logger.getLogger(ScreenCaptureListener.class);

    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        logger.info("【" + tr.getName() + " Start】");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        logger.info("【" + tr.getName() + " Failure】");
        takeScreenShot(tr);
    }

    public void takeScreenShot(ITestResult tr) {
        String instanceName = tr.getInstanceName();
        String suitName = tr.getTestContext().getSuite().getName();
        String partName = (suitName + "_" + tr.getTestContext().getName() + "_" + instanceName).replaceAll("\\.", "_");
        String folder = ScreenCaptureListener.class.getClassLoader().getResource("test-output").getPath();
        String filename = folder + "/img/" + partName + ".png";
        BaseTest baseTestcase = (BaseTest) tr.getInstance();
        baseTestcase.takeScreen(filename);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        takeScreenShot(tr);
        logger.info("【" + tr.getName() + " Skipped】");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        logger.info("【" + tr.getName() + " Success】");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }
}
