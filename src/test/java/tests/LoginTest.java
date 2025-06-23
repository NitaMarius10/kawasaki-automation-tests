package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;


@Epic("Login Functionality")
@Feature("Login with valid credentials")
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 2, description = "Login using valid data and login validation")
    @Story("Valid user login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify if a user can log in with email and password" +
            " valid and if it is redirected correctly")
    public void testLoginUser() {
        String email = "mirkocestu@gufum.com";
        String password = "Test123.#";

        Allure.step("We start the login process for the user: " + email);
        LoginPage.loginUser(email, password);

        // We verify that the user is logged in
        String currentUrl = driver.getCurrentUrl();
        Allure.step("URL after login: " + currentUrl);

        Assert.assertTrue(currentUrl.toLowerCase().contains("account") ||
                        currentUrl.toLowerCase().contains("dashboard") ||
                        !currentUrl.toLowerCase().contains("login"),
                "The user does not appear to be logged in. Current URL: " + currentUrl);

        Allure.step("Login confirmed – the user is redirected correctly");
    }
}
