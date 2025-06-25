
package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import utils.DataValidator;

import java.time.Duration;

public class AccountPage extends BasePage {

    private final WebDriverWait wait;
    private static final String SIGNUP_URL = Config.get("signup.url");

    public AccountPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCreateAccount() {
        Allure.step("We navigate to the account creation page");
        driver.get(SIGNUP_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_NAME_AND_LASTNAME));
    }

    public void enterFullName(String name) {
        Allure.step("Enter the name: " + name);
        sendText(AccountPageLocators.FIELD_NAME_AND_LASTNAME, name);
    }

    public void enterPhone(String phone) {
        Allure.step("Enter your phone number: " + phone);
        sendText(AccountPageLocators.FIELD_PHONE, phone);
    }

    public void enterEmail(String email) {
        Allure.step("Enter your email address: " + email);
        sendText(AccountPageLocators.FIELD_EMAIL, email);
    }

    public void enterPassword(String password) {
        Allure.step("Enter the password: [ascuns]");
        sendText(AccountPageLocators.FIELD_PASSWORD, password);
    }

    public void confirmPassword(String password) {
        Allure.step("Confirm password: [ascuns]");
        sendText(AccountPageLocators.FIELD_CONFIRM_PASSWORD, password);
    }

    public void acceptTerms() {
        Allure.step("We check the terms and conditions");
        try {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(AccountPageLocators.FIELD_CHECKBOX_TERMS));
            scrollToElement(checkbox);
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        } catch (ElementClickInterceptedException e) {
            Allure.step("Checkbox click failed - let's try with JavaScript");
            WebElement checkbox = driver.findElement(AccountPageLocators.FIELD_CHECKBOX_TERMS);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    public void clickCreateAccount() {
        Allure.step("Click on the create account button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(AccountPageLocators.BUTTON_CREATE_ACCOUNT));
        scrollToElement(button);
        button.click();
        wait.until(ExpectedConditions.urlContains("contul-meu"));
    }

    public void createAccount(String fullName, String phone, String email, String password) {
        Allure.step("We start filling out the form");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        confirmPassword(password);
        acceptTerms();
        clickCreateAccount();
        Allure.step("I submitted the account creation form");
    }

    public String getFullNameValue() {
        return getFieldValue(AccountPageLocators.FIELD_NAME_AND_LASTNAME);
    }

    public String getPhoneValue() {
        return getFieldValue(AccountPageLocators.FIELD_PHONE);
    }

    public String getEmailValue() {
        return getFieldValue(AccountPageLocators.FIELD_EMAIL);
    }

    public String getPasswordValue() {
        return getFieldValue(AccountPageLocators.FIELD_PASSWORD);
    }

    public void verifyFormData(String expectedName, String expectedPhone, String expectedEmail, String expectedPassword) {
        Allure.step("We verify the data filled in the form");
        DataValidator.validateEquals("Name", expectedName, getFullNameValue());
        DataValidator.validateEquals("Phone", expectedPhone, getPhoneValue());
        DataValidator.validateEquals("Email", expectedEmail, getEmailValue());
        DataValidator.validateEquals("Password", expectedPassword, getPasswordValue());
    }

    // Helper methods
    private void sendText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    private String getFieldValue(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute("value").trim();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
