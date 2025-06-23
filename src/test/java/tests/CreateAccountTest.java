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



/*
package tests;

import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.LoginPage;
import utils.ExtentTestManager;
import utils.TestLogWriter;
import java.io.IOException;
import java.time.Duration;


//Test for creating a new account, using Page Object Model + ExtentReports + Logger.
//User: Cuza Alexandru | Email: mirkocestu@gufum.com | Password: Test123.


public class CreateAccountTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(CreateAccountTest.class);
    private AccountPage accountPage;
    private LoginPage loginPage;
    private TestLogWriter logWriter;

    //public CreateAccountTest(WebDriver driver) {
    //    super();
    //}

    //@Override
    public String getName() {
        return "Create Account Test";
    }

    @BeforeClass
    public void setUp() {
        super.baseSetUp();// Initialize the driver (from BaseTest) and the pages
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        accountPage = new AccountPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testCreateAccountWithValidData() throws IOException {
        ExtentTestManager.startTest("Create Account", "Test de creare cont valid");

        try {
            logWriter = new TestLogWriter("CreateAccountTest");
            ExtentTestManager.getTest().assignCategory("AccountTests");
            ExtentTestManager.getTest().log(Status.INFO, "Începem testul de creare cont pentru un utilizator valid");

            log.info("Navigăm la pagina de creare cont");
            logWriter.log("Navigăm la pagina de creare cont");
            accountPage.navigateToCreateAccount();
            LoginPage.closeCookieBanner();
            logWriter.log("Am inchis frame-ul de cookie");

            // Date de test
            String name = "Cuza Alexandru";
            logWriter.log("Am introdus Numele: Cuza Alexandru");
            String phone = "0731194933";
            logWriter.log("Am introdus Numarul de telefon: 0731194933");
            String email = "mirkocestu@gufum.com";
            logWriter.log("Am introdus adresa de e-mail: mirkocestu@gufum.com");
            String password = "Test123.#";
            logWriter.log("Am introdus parola: Test123.#");

            logWriter.log("Completăm formularul: " + name + ", " + email);
            ExtentTestManager.getTest().log(Status.INFO, "Completăm datele de creare cont");
            accountPage.createAccount(name, phone, email, password);


            logWriter.log("Testul: CreateAccount s-a finalizat cu succes");
        } catch (Exception e) {
            log.error("Eroare în test: ", e);
            ExtentTestManager.getTest().log(Status.FAIL, "Testul a eșuat: " + e.getMessage());
            logWriter.log("Testul a eșuat: " + e.getMessage());
            throw e;
        } finally {
            if (logWriter != null) logWriter.close();
            ExtentTestManager.endTest();
        }


    }
}


 */