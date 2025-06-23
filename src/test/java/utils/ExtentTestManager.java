package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;

import java.util.HashMap;
import java.util.Map;

/*
  The helper class that manages ExtentTest instances per-thread for reporting.
 */
public class ExtentTestManager {

    private static Map<Integer, ExtentTest> testMap = new HashMap<>();
    private static ExtentReports extent = ExtentManager.getInstance();

    public static synchronized ExtentTest getTest() {
        return testMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.flush();
    }

    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}
