package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    @Step("Driver initialization")
    public void baseSetUp() {
        driver = DriverFactory.getDriver("chrome"); // get a configured instance of ChromeDriver
        driver.manage().window().maximize();
    }

    @AfterClass
    @Step("Close driver")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}