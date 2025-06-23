package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Demo")
@Feature("Allure Check")
public class AllureDemoTest {

    @Test(description = "Dummy test to verify correct generation of steps in the Allure report")
    @Story("Allure Integration Check")
    @Severity(SeverityLevel.MINOR)
    @Description("Demo test using Allure.step(...) to confirm Allure functionality.")
    public void verifyAllureStepsAppear() {
        Allure.step("Step 1: Start the AllureDemo test");

        Allure.step("Step 2: Simulate an action");

        Allure.step("Step 3: We check a fictitious condition");
        boolean success = true;
        assert success;

        Allure.step("Step 4: Dummy test completed successfully!");
    }
}
