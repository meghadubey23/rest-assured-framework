package Utilities;

import org.junit.Assert;

public class AssertUtility {

    public static void assertEquals(Object actual, Object expected, Object value) {
        Assert.assertEquals(actual, expected);
        System.out.println("Assert value of " + value + " - " + actual + " equals " + expected);
    }
}
