package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

@Epic("Login Functionality")
@Feature("Login with invalid credentials")
public class InvalidLoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver.get("https://www.motocicletekawasaki.ro/login");
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void refreshPage() {
        driver.navigate().refresh();
        if (driver.getCurrentUrl().contains("login-customer")) {
            driver.navigate().to("https://www.motocicletekawasaki.ro/login");
        }
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"testuser@", "Parola123", "Please enter a part following '@'"},
                {"user@gmail.com", "gresit", "Parola a fost introdusa gresit"},
                {"", "", "Please fill out this field"}
        };
    }

    @Test(priority = 4, dataProvider = "invalidLoginData",
            description = "We validate error messages for invalid login")
    @Story("User cannot log in with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Description("Negative login test that validates error messages for " +
            "various invalid email & password combinations.")
    public void testInvalidLogin(String email, String password, String expectedMessage) {
        Allure.step("We start the invalid login with: " + email + " / " + password);

        loginPage.closeCookieBanner();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnAuthentification();

        if (loginPage.isValidationPreventedByBrowser()) {
            Allure.step("Validation was done by the browser – we do not check the backend message");
            return;
        }

        String actualError = loginPage.getErrorMessage();
        Allure.step("Message received: " + actualError);
        Allure.step("We expect to: " + expectedMessage);

        Assert.assertNotNull(actualError, "No error message was returned");
        Assert.assertTrue(
                actualError.toLowerCase().contains(expectedMessage.toLowerCase()),
                "The received message does not contain the expected message.\nExpected: " + expectedMessage + "\nActual: " + actualError
        );

        Allure.step("The error message is correct");
    }
}



/*
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class InvalidLoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.motocicletekawasaki.ro/login");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver); // Inițializăm pagina LoginPage

    }

    @BeforeMethod
    public void refreshPage() {
        driver.navigate().refresh(); // Reîncarcă pagina înainte de fiecare test
        if (driver.getCurrentUrl().contains("login-customer")) {
            driver.navigate().to("https://www.motocicletekawasaki.ro/login");
        }
    }

    @DataProvider(name = "invalidLoginData")
    // public Object[][] → The method returns a two-dimensional array ([][]) of objects.
    // The first dimension ([]) → Represents the number of data sets (3 in this case).
    // The second dimension ([]) → Represents the number of parameters per test (email and password).
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"testuser@", "Parola123", "Please enter a part following '@'.'testuser@' is incomplete."}, // Email is invalid
                {"user@gmail.com", "gresit", "Parola a fost introdusa gresit"}, // Incorrect password
                {"", "", "Please fill out this field"}  // Empty fields
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void testInvalidLogin(String email, String password, String expectedMessage) {
        //loginPage.navigateToLoginPage();
        loginPage.closeCookieBanner();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnAuthentification();

        if (loginPage.isValidationPreventedByBrowser()) {
            System.out.println("The validation was done by the browser. No error message from the backend");
            return; // you no longer check the errorMessage, as it does not appear
        }

        Assert.assertTrue(loginPage.getErrorMessage().contains("Eroare"),
                "Missing error message!");
        // Debug
        String actualError = loginPage.getErrorMessage();
        System.out.println("Message received from the site: " + actualError);
        System.out.println("Expected message in test: " + expectedMessage);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Real error message returned: [" + actualError + "]");
        System.out.println("Message expected in test: [" + expectedMessage + "]");

        Assert.assertTrue(actualError != null && actualError.toLowerCase().contains(expectedMessage.toLowerCase()),
                "The message does not contain the expected text. Expected: [" + expectedMessage + "] dar s-a primit: [" + actualError + "]");

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

 */