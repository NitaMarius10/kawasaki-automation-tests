package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
  ExtentManager handles the initialization of the ExtentReports singleton.
  This class adds useful system information, supports XML configuration, and
  creates a unique HTML report for each run.
 */
public class ExtentManager {

    // The singleton instance of ExtentReports
    private static ExtentReports extent;

    // Return the ExtentReports instance (created only once)
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    // Create an instance of the Extent report with a unique timestamp
    private static ExtentReports createInstance() {
        // Create a unique filename based on the current date/time
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "test-output/extent-report_" + timestamp + ".html";

        // Create the SparkReporter object with the location of the HTML file.
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        // Load the external XML configuration file
        try {
            sparkReporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        } catch (Exception e) {
            System.out.println("Nu s-a putut încărca fișierul XML: " + e.getMessage());
        }

        // Create the ExtentReports object and attach the reporter.
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system information for the report
        extent.setSystemInfo("Tester", "Nita Marius");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }

    // Called at the end of the suite to close and save the report.
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
