
package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static void navigateToLoginUser() {
        Allure.step("Navigate to the login page");
        if (driver != null) {
            driver.get("https://www.motocicletekawasaki.ro/login");
        } else {
            Allure.step("WebDriver is null - cannot navigate");
        }
    }

    public static void closeCookieBanner() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookiescript_reject")));
            cookieButton.click();
            Allure.step("The cookie banner has been closed successfully");
        } catch (TimeoutException e) {
            Allure.step("The cookie banner was not found or was already closed");
        }
    }

    public void navigateToLoginPage() {
        if (!driver.getCurrentUrl().contains("/login")) {
            Allure.step("Redirect to /login");
            driver.navigate().to("https://www.motocicletekawasaki.ro/login");
        }
    }

    public boolean isValidationPreventedByBrowser() {
        return driver.getCurrentUrl().contains("/login");
    }

    public static void enterEmail(String email) {
        Allure.step("Fill in the Email field: " + email);
        driver.findElement(LoginPageLocators.FIELD_EMAIL_CONFIRMATION).sendKeys(email);
    }

    public static void enterPassword(String password) {
        Allure.step("We fill in the Password field: [hidden]");
        driver.findElement(LoginPageLocators.FIELD_PASSWORD_CONFIRMATION).sendKeys(password);
    }

    public static void clickOnAuthentification() {
        Allure.step("We press the authentication button");
        driver.findElement(LoginPageLocators.BUTTON_USER_LOGIN).click();
    }

    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // We wait until any of the known error messages appear
            By[] possibleLocators = new By[] {
                    By.xpath("//div[contains(@class, 'alert')]"),
                    By.xpath("//li[contains(text(),'Parola')]"),
                    By.xpath("//li[contains(text(),'email')]")
            };

            for (By locator : possibleLocators) {
                try {
                    WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                    String message = errorElement.getText().trim();
                    if (!message.isEmpty()) {
                        Allure.step("Error message captured: " + message);
                        return message;
                    }
                } catch (Exception ignored) {
                    // We continue with the next locator
                }
            }

            Allure.step("No visible error messages were found in known locations");
            return "";

        } catch (Exception e) {
            Allure.step("Unexpected error while retrieving error message: " + e.getMessage());
            return "";
        }
    }

    public static void loginUser(String email, String password) {
        Allure.step("We start the authentication process for the user: " + email);
        navigateToLoginUser();
        closeCookieBanner();
        enterEmail(email);
        enterPassword(password);
        clickOnAuthentification();
        Allure.step("Completing the authentication process...");
    }
}
