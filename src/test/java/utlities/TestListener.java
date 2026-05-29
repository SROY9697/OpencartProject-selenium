package utlities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener {

    ExtentReports extent =
            ExtentReportManager.getReportInstance();

    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(
                result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        WebDriver driver = null;

        try {
            driver = (WebDriver) result.getTestClass()
                    .getRealClass()
                    .getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            ExtentReportManager.attachScreenshot(
                    test,
                    driver,
                    result.getMethod().getMethodName()
            );
        }
        System.out.println("Driver Null? " + (driver == null));
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Extent Report Generated");
    }
}