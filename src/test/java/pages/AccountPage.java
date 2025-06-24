
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
    private static final String SIGNUP_URL = Config.get("signup.url"); // externizat

    public AccountPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCreateAccount() {
        Allure.step("Navigăm la pagina de creare cont");
        driver.get(SIGNUP_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_NAME_AND_LASTNAME));
    }

    public void enterFullName(String name) {
        Allure.step("Introducem numele: " + name);
        sendText(AccountPageLocators.FIELD_NAME_AND_LASTNAME, name);
    }

    public void enterPhone(String phone) {
        Allure.step("Introducem numărul de telefon: " + phone);
        sendText(AccountPageLocators.FIELD_PHONE, phone);
    }

    public void enterEmail(String email) {
        Allure.step("Introducem adresa de email: " + email);
        sendText(AccountPageLocators.FIELD_EMAIL, email);
    }

    public void enterPassword(String password) {
        Allure.step("Introducem parola: [ascuns]");
        sendText(AccountPageLocators.FIELD_PASSWORD, password);
    }

    public void confirmPassword(String password) {
        Allure.step("Confirmăm parola: [ascuns]");
        sendText(AccountPageLocators.FIELD_CONFIRM_PASSWORD, password);
    }

    public void acceptTerms() {
        Allure.step("Bifăm termenii și condițiile");
        try {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(AccountPageLocators.FIELD_CHECKBOX_TERMS));
            scrollToElement(checkbox);
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        } catch (ElementClickInterceptedException e) {
            Allure.step("Click pe checkbox eșuat - încercăm cu JavaScript");
            WebElement checkbox = driver.findElement(AccountPageLocators.FIELD_CHECKBOX_TERMS);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    public void clickCreateAccount() {
        Allure.step("Click pe butonul de creare cont");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(AccountPageLocators.BUTTON_CREATE_ACCOUNT));
        scrollToElement(button);
        button.click();
        wait.until(ExpectedConditions.urlContains("contul-meu")); // ar trebui să fie și acesta externizat
    }

    public void createAccount(String fullName, String phone, String email, String password) {
        Allure.step("Începem completarea formularului");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        confirmPassword(password);
        acceptTerms();
        clickCreateAccount();
        Allure.step("Am trimis formularul de creare cont");
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
        Allure.step("Verificăm datele completate în formular");
        DataValidator.validateEquals("Nume", expectedName, getFullNameValue());
        DataValidator.validateEquals("Telefon", expectedPhone, getPhoneValue());
        DataValidator.validateEquals("Email", expectedEmail, getEmailValue());
        DataValidator.validateEquals("Parolă", expectedPassword, getPasswordValue());
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

/*
package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataValidator;

import java.time.Duration;

public class AccountPage extends BasePage {

    private final WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCreateAccount() {
        Allure.step("We navigate to the account creation page");
        driver.get("https://www.motocicletekawasaki.ro/sign-up");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_NAME_AND_LASTNAME));
    }

    public void enterFullName(String name) {
        Allure.step("Enter the name: " + name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_NAME_AND_LASTNAME))
                .sendKeys(name);
    }

    public void enterPhone(String phone) {
        Allure.step("Enter your phone number: " + phone);
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_PHONE))
                .sendKeys(phone);
    }

    public void enterEmail(String email) {
        Allure.step("Enter your email: " + email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_EMAIL))
                .sendKeys(email);
    }

    public void enterPassword(String password) {
        Allure.step("Enter the password: [hidden]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_PASSWORD))
                .sendKeys(password);
    }

    public void confirmPassword(String password) {
        Allure.step("Confirm password: [hidden]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_CONFIRM_PASSWORD))
                .sendKeys(password);
    }

    public void acceptTerms() {
        Allure.step("We accept the terms and conditions");
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(AccountPageLocators.FIELD_CHECKBOX_TERMS));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void clickCreateAccount() {
        Allure.step("Click on the create account button");
        wait.until(ExpectedConditions.elementToBeClickable(AccountPageLocators.BUTTON_CREATE_ACCOUNT)).click();
        // We are waiting for redirection or visual confirmation
        wait.until(ExpectedConditions.urlContains("contul-meu"));
    }

    public void createAccount(String fullName, String phone, String email, String password) {
        Allure.step("We start filling out the account form");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        confirmPassword(password);
        acceptTerms();
        clickCreateAccount();
        Allure.step("I submitted the account creation form");
    }

    // Get Values for Validation

    public String getFullNameValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_NAME_AND_LASTNAME))
                .getAttribute("value").trim();
    }

    public String getPhoneValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_PHONE))
                .getAttribute("value").trim();
    }

    public String getEmailValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_EMAIL))
                .getAttribute("value").trim();
    }

    public String getPasswordValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.FIELD_PASSWORD))
                .getAttribute("value").trim();
    }

    public void verifyFormData(String expectedName, String expectedPhone, String expectedEmail, String expectedPassword) {
        Allure.step("We verify the data filled in the form");
        DataValidator.validateEquals("Name", expectedName, getFullNameValue());
        DataValidator.validateEquals("Phone", expectedPhone, getPhoneValue());
        DataValidator.validateEquals("Email", expectedEmail, getEmailValue());
        DataValidator.validateEquals("Password", expectedPassword, getPasswordValue());
    }
}


 */