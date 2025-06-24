package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.LoginPage;

@Epic("Account Functionality")
@Feature("Account Creation")
public class CreateAccountTest extends BaseTest {

    private AccountPage accountPage;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        accountPage = new AccountPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Creating a new account with valid data")
    @Story("User can register with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Complete test that validates the account creation flow for a user with valid data, " +
            "including checking values \u200B\u200Bbefore submission.")
    public void testCreateAccountWithValidData() {
        String name = "Cuza Alexandru";
        String phone = "0731194933";
        String email = "mirkocestu@gufum.com";
        String password = "Test123.#";

        // 1. Navigate to the account creation page
        accountPage.navigateToCreateAccount();
        LoginPage.closeCookieBanner();

        // 2. We fill out the form step by step
        accountPage.enterFullName(name);
        accountPage.enterPhone(phone);
        accountPage.enterEmail(email);
        accountPage.enterPassword(password);
        accountPage.confirmPassword(password);

        // 3. We validate that the values filled in are the expected ones (before submitting the form)
        accountPage.verifyFormData(name, phone, email, password);

        // 4. We accept the terms and submit the form
        accountPage.acceptTerms();
        accountPage.clickCreateAccount();
    }
}
