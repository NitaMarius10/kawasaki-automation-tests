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
