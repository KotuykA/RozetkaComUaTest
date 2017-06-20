package ua.com.rozetka.core;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ua.com.rozetka.util.PropertiesCache;
import ua.com.rozetka.util.WebDriverUtils;

public class TestListener implements ITestListener {
    private WebDriverUtils webDriverUtils = new WebDriverUtils();
    private final String SCREENSHOTS_FOR_SUCCESS_PATH = ("screenshots.path.success");
    private final String SCREENSHOTS_FOR_FAILED_PATH = ("screenshots.path.failed");

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("on test method start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        makeScreenShot(result, PropertiesCache.getInstance().getProperty(SCREENSHOTS_FOR_SUCCESS_PATH));
        System.out.println("on test method success, make a screenshot for "  );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenShot(result, PropertiesCache.getInstance().getProperty(SCREENSHOTS_FOR_FAILED_PATH));
        System.out.println("on test method failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("test method skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("test failed but within success % ");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("on start of test " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("on finish of test " + context.getName());
    }

    private void makeScreenShot(ITestResult result, String screenShotPath) {
        WebDriver driver = ((WebDriverTestBase) result.getInstance()).driver;
        webDriverUtils.saveScreenshot(driver, screenShotPath, result.getMethod().getMethodName());
    }
}
