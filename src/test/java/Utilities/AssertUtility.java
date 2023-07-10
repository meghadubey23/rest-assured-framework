package Utilities;

public class AssertUtility {

    public static void assertEquals(String actual, String expected) {
        org.testng.Assert.assertEquals(actual, expected);
    }
}
