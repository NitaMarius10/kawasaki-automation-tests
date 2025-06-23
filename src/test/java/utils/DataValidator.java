package utils;

import io.qameta.allure.Allure;
import org.testng.Assert;

public class DataValidator {

    public static void validateEquals(String fieldName, Object expected, Object actual) {
        Allure.step("Validate " + fieldName + ": expect = " + expected + ", actual = " + actual);
        Assert.assertEquals(actual, expected, fieldName + " does not correspond");
    }
}
