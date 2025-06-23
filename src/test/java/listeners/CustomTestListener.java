package listeners;

import com.aventstack.extentreports.*;
import org.openqa.selenium.*;
import org.testng.*;
import tests.BaseTest;
import io.qameta.allure.Allure;

import java.io.*;
import java.nio.file.*;

public class CustomTestListener implements ITestListener {


    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static int testCounter = 1;

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String testName = "Test" + (testCounter++) + " - " + className + "." + methodName;

        ExtentTest test = extent.createTest(testName)
                .assignCategory(className)
                .assignDevice(System.getProperty("os.name"));

        extentTest.set(test);
        test.log(Status.INFO, "Test started: `" + methodName + "` from `" + className + "`");

        Allure.step("Test started: " + methodName + " (" + className + ")");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "The test passed successfully");
        Allure.step("The test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        String testMethod = result.getMethod().getMethodName();
        Throwable cause = result.getThrowable();

        test.log(Status.FAIL, "Test failed: " + cause);

        Object testInstance = result.getInstance();
        WebDriver driver = ((BaseTest) testInstance).getDriver();

        if (driver != null) {
            try {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String screenshotFile = "./screenshots/" + testMethod + ".png";
                Files.write(Paths.get(screenshotFile), screenshotBytes);

                test.addScreenCaptureFromPath(screenshotFile);
                Allure.getLifecycle().addAttachment("Screenshot on Failure", "image/png", "png",
                        new ByteArrayInputStream(screenshotBytes));

                Allure.step("Test failed: attached capture");

            } catch (IOException e) {
                test.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
                Allure.step("Could not generate screenshot");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "The test was skipped");
        Allure.step("Skipped test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("We start the execution of the test suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("We are completing the suite execution: " + context.getName());
    }
}

