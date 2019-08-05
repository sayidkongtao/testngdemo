package com.sayid.listener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.testng.listener.commons.ExtentTestCommons;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExtentTestNGIReporterListener implements IReporter {
    //生成的路径以及文件名
    private static final String OUTPUT_FOLDER = ExtentTestNGIReporterListener.class.getClassLoader().getResource("test-output").getPath();
    private static final String FILE_NAME = "index.html";

    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                ExtentTest suiteTest = extent.createTest(context.getName());
                buildTestNodes(suiteTest, context.getFailedTests(), Status.FAIL);
                buildTestNodes(suiteTest, context.getSkippedTests(), Status.SKIP);
                buildTestNodes(suiteTest, context.getPassedTests(), Status.PASS);
            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();
    }

    private void buildTestNodes(ExtentTest suiteTest, IResultMap tests, Status status) {
        ExtentTest testNode;
        ExtentTest classNode;
        Map<String, ExtentTest> classTestMethodMap = new HashMap<>();

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                String className = result.getInstance().getClass().getSimpleName();

                if (classTestMethodMap.containsKey(className)) {
                    classNode = classTestMethodMap.get(className);
                } else {
                    classNode = suiteTest.createNode(className);
                    classTestMethodMap.put(className, classNode);
                }

                testNode = classNode.createNode(result.getMethod().getMethodName(),
                        result.getMethod().getDescription());

                String[] groups = result.getMethod().getGroups();
                ExtentTestCommons.assignGroups(testNode, groups);

                if (result.getThrowable() != null) {
                    String instanceName = result.getInstanceName();
                    String suitName = result.getTestContext().getSuite().getName();
                    String partName = (suitName + "_" + result.getTestContext().getName() + "_" + instanceName).replaceAll("\\.", "_");
                    String folder = ExtentTestNGIReporterListener.class.getClassLoader().getResource("test-output").getPath();
                    String filename = folder + "/img/" + partName + ".png";

                    try {
                        testNode.addScreenCaptureFromPath(filename);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    testNode.log(status, result.getThrowable());

                } else {
                    testNode.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                testNode.getModel().getLogContext().getAll().forEach(x -> x.setTimestamp(getTime(result.getEndMillis())));
                testNode.getModel().setStartTime(getTime(result.getStartMillis()));
                testNode.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }


    private void init() {
        File reportDir = new File(OUTPUT_FOLDER);
        if (!reportDir.exists() && !reportDir.isDirectory()) {
            reportDir.mkdir();
        }

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + "/" + FILE_NAME);
        htmlReporter.config().setDocumentTitle("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setReportName("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
        htmlReporter.config().setJS("$(document).ready(function() { eles = $(\".screenshots li img\"); for (ele of eles ) { var names = ele.getAttributeNames(); for(name of names) { if (name != \"width\") {var attri = ele.getAttribute(name); ele.setAttribute(name, attri.replace(/.+test-output\\/(?=img)/g,\"\"))} } } });");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}

