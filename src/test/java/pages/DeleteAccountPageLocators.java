package pages;

import org.openqa.selenium.By;

public class DeleteAccountPageLocators {

    public static final By BUTTON_ADMINISTREAZA_DATELE_TALE = By.xpath(
            "//a[normalize-space()='Administreaza datele tale']");

    public static final By BUTTON_DELETE_ACCOUNT = By.xpath(
            "//button[@data-bs-target ='#deleteAccount']");

    public static final By FIELD_CONFIRM_PASSWORD = By.xpath(
            "//input[@class ='form-control mt-2']");

    public static final By BUTTON_DELETE_ACCOUNT_CONFIRMATION = By.xpath(
            "//button[@class ='modal-cancel-button bg-white px-2 py-2 border-0 text-danger']");

}
