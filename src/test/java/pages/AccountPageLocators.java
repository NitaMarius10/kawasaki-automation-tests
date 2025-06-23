package pages;

import org.openqa.selenium.By;


 //The class that contains only the locators of the elements on the account creation page

public class AccountPageLocators {

    // Locator for the first and last name field
    public static final By FIELD_NAME_AND_LASTNAME = By.xpath(
            "//input[@placeholder='Nume Si Prenume']");
    // Phone field locator
    public static final By FIELD_PHONE = By.xpath("//input[@name='phone']");
    // Email field locator
    public static final By FIELD_EMAIL = By.xpath("//input[@placeholder='E-mail:']");
    // Password field locator
    public static final By FIELD_PASSWORD = By.id("inputPassword");
    // Password confirmation field locator
    public static final By FIELD_CONFIRM_PASSWORD = By.id("confirmPassword");
    // Locator for terms and conditions checkbox
    public static final By FIELD_CHECKBOX_TERMS = By.xpath("//input[@name='terms']");
    // Create account button locator
    public static final By BUTTON_CREATE_ACCOUNT = By.xpath(
            "//button[@class='btn continue btn-login-continue semi-rounded-border']");


}
