package listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.testng.*;
import tests.BaseTest;

import java.io.*;
import java.nio.file.*;

public class CustomTestListener implements ITestListener {

    private static int testCounter = 1;

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String testName = "Test" + (testCounter++) + " - " + className + "." + methodName;

        Allure.step("Started test: `" + methodName + "` from class `" + className + "`");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Throwable cause = result.getThrowable();

        Allure.step("Test failed: " + methodName + " | Cause: " + (cause != null ? cause.getMessage() : "Unknown"));

        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();

            if (driver != null) {
                try {
                    byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    String screenshotDir = "./screenshots/";
                    String screenshotPath = screenshotDir + methodName + ".png";

                    Files.createDirectories(Paths.get(screenshotDir));
                    Files.write(Paths.get(screenshotPath), screenshotBytes);

                    Allure.getLifecycle().addAttachment(
                            "Screenshot on failure", "image/png", "png", new ByteArrayInputStream(screenshotBytes)
                    );

                    Allure.step("Screenshot captured for failed test");

                } catch (IOException e) {
                    Allure.step("Could not capture screenshot: " + e.getMessage());
                }
            } else {
                Allure.step("WebDriver was null, no screenshot taken");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.step("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
    }
}