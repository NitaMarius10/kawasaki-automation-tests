package pages;

import org.openqa.selenium.By;

public class LoginPageLocators {

    public static final By LOGIN_INITIALIZATION = By.xpath(
            "//span[@class='d-none d-sm-block text-uppercase']");

    public static final By FIELD_EMAIL_CONFIRMATION = By.xpath(
            "//div//input[@class='form-control']");

    public static final By FIELD_PASSWORD_CONFIRMATION = By.id("password");

    public static final By BUTTON_USER_LOGIN = By.xpath("//div//button[text()='Autentificare']");


    // Error message locators
    public static final By GENERIC_ERROR_MESSAGE = By.xpath(
            "//div//p[@class='text-danger mt-2 error-info']");

}
