
package com.srm.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.srm.framework.reports.ExtentManager;
import com.srm.framework.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> EXTENT_TEST = new ThreadLocal<>();
    private static final ExtentReports EXTENT_REPORTS = ExtentManager.getExtentReports();

    @Override
    public void onTestStart(ITestResult result) {
        EXTENT_TEST.set(EXTENT_REPORTS.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        EXTENT_TEST.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtils.capture(result.getMethod().getMethodName());
        EXTENT_TEST.get().log(Status.FAIL, result.getThrowable());
        try {
            EXTENT_TEST.get().fail("Failure screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception exception) {
            EXTENT_TEST.get().log(Status.WARNING, "Screenshot could not be attached: " + exception.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        EXTENT_TEST.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT_REPORTS.flush();
        EXTENT_TEST.remove();
    }
}
