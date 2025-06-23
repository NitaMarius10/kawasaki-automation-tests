package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;


  //ScreenshotUtil provides screen capture functionality
public class ScreenshotUtil {
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }


    public static void takeFailedResultScreenshot(ITestResult testResult) {
        if (driver == null) {
            System.out.println("Driver is null, cannot take screenshot.");
            return;
        }
        if (testResult.getStatus() == ITestResult.FAILURE) {
            try {
                // Converting the driver to the TakesScreenshot interface
                TakesScreenshot screenshot = (TakesScreenshot) ScreenshotUtil.driver;
                File source = screenshot.getScreenshotAs(OutputType.FILE);

                // Creating a directory for saving screenshots, if it does not exist
                File destinationDir = new File(System.getProperty("user.dir") +
                                                               "/resources/screenshots/");
                if (!destinationDir.exists()) {
                    destinationDir.mkdirs();
                }

                // Generating a high-precision timestamp to create a unique name
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS");
                String timeStamp = LocalDateTime.now().format(formatter);

                // Building the file name, for example: "20250529_135300123_testName.png"
                File destination = new File(destinationDir, timeStamp + "_" + testResult.getName() + ".png");

                // Copying the file without the overwrite option,the name will be unique
                Files.copy(source.toPath(), destination.toPath());

                System.out.println("Screenshot Located At " + destination.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("Error while saving the screenshot", e);
            }
        }
    }
}