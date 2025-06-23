
package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

public class DeleteAccountPage extends BasePage {

    public DeleteAccountPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnDeleteAccount() {
        Allure.step("We click on the 'Stergere cont' button");
        driver.findElement(DeleteAccountPageLocators.BUTTON_DELETE_ACCOUNT).click();
    }

    public void fieldConfirmPassword(String password) {
        Allure.step("Enter your password to confirm account deletion");
        driver.findElement(DeleteAccountPageLocators.FIELD_CONFIRM_PASSWORD).sendKeys(password);
    }

    public void deleteAccountConfirmation() {
        Allure.step("We confirm account deletion");
        driver.findElement(DeleteAccountPageLocators.BUTTON_DELETE_ACCOUNT_CONFIRMATION).click();
    }

    public void deleteAccountFlow(String email, String password) {
        Allure.step("We are initiating the account deletion process for: " + email);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);

        clickOnDeleteAccount();
        fieldConfirmPassword(password);
        deleteAccountConfirmation();

        Allure.step("Account deleted (if there was successful confirmation)");
    }
}
