package com.upgrade.listeners;

import com.upgrade.pages.BasePage;
import com.upgrade.tests.AbstractTest;
import lombok.SneakyThrows;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ITestListenerImpl implements ITestListener {

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        AbstractTest test =(AbstractTest) result.getInstance();
        String path = BasePage.takeScreenshot(test.getDriver());
        Reporter.log(path);
    }
}
