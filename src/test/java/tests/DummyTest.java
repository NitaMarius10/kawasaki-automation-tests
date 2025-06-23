package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class DummyTest {

    @Test
    public void dummyAllureTest() {
        Allure.step("We verify that Allure is working correctly");
    }

}
