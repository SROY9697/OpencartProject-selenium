package utlities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utlities.ScreenshotUtil;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            // Reports folder
            String reportDir =
                    System.getProperty("user.dir") + "/reports/";

            File dir = new File(reportDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Timestamp
            String timeStamp = new SimpleDateFormat(
                    "yyyy.MM.dd.HH.mm.ss")
                    .format(new Date());

            // Report path
            String reportPath =
                    reportDir + "ExtentReport_" +
                    timeStamp + ".html";
            
            System.out.println("Report Path: " + reportPath);

            // Spark Reporter
            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            // Configurations
            sparkReporter.config()
                    .setDocumentTitle("Automation Report");

            sparkReporter.config()
                    .setReportName("Selenium Execution Report");

            sparkReporter.config()
                    .setTheme(Theme.STANDARD);

            // Main report object
            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            // System Info
            extent.setSystemInfo("Project",
                    "Selenium Automation");

            extent.setSystemInfo("Tester",
                    "Your Name");

            extent.setSystemInfo("OS",
                    System.getProperty("os.name"));

            extent.setSystemInfo("Java Version",
                    System.getProperty("java.version"));
        }

        return extent;
    }

    // Method to attach screenshot in report
    public static void attachScreenshot(
            ExtentTest test,
            WebDriver driver,
            String testName) {

        try {

            System.out.println("Inside attachScreenshot");

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            driver,
                            testName);

            System.out.println("Screenshot Path: " + screenshotPath);

            File file = new File(screenshotPath);

            System.out.println("File Exists? " + file.exists());

            test.fail("Screenshot",
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(
                                    file.getAbsolutePath())
                            .build());

            System.out.println("Screenshot attached successfully");

        } catch (Exception e) {

            System.out.println("Screenshot attachment failed");

            e.printStackTrace();
        }
    }
}