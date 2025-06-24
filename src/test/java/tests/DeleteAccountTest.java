package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DeleteAccountPage;
import pages.LoginPage;

@Epic("Account Management")
@Feature("Account Deletion")
public class DeleteAccountTest extends BaseTest {

    private DeleteAccountPage deleteAccountPage;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        loginPage = new LoginPage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);
    }

    @Test(priority = 3, description = "Account deletion and validation of the impossibility of" +
            " subsequent authentication")
    @Story("User can delete their account")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test that checks whether a user can delete the account and whether " +
            "subsequent login with the same data fails")
    public void deleteAccountTest() {
        String email = "mirkocestu@gufum.com";
        String password = "Test123.#";

        // 1. We execute the account deletion flow
        deleteAccountPage.deleteAccountFlow(email, password);

        // 2. We try to login with the same credentials to confirm the deletion
        loginPage.navigateToLoginPage();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnAuthentification();

        // 3. Capture and validate the error message
        String actualError = loginPage.getErrorMessage();
        System.out.println("Actual error message after deletion: " + actualError);
        Allure.step("Error message at login after deletion: " + actualError);

        // We validate that login fails as proof of deletion
        Assert.assertNotNull(actualError, "Error message missing - account still appears to exist");
        Assert.assertTrue(
                actualError.toLowerCase().contains("parola a fost introdusa gresit") ||
                        actualError.toLowerCase().contains("email") ||
                        actualError.toLowerCase().contains("non-existent"),
                "The error message does not indicate that the login failed after deletion"
        );

        // 4. Logical confirmation of deletion (in the absence of a confirmation from the UI)
        Allure.step("We confirm that the account deletion was effective – subsequent authentication failed");
    }
}
