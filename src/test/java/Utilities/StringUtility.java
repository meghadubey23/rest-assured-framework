package Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtility {

    public static String randomString(int size) {
        return RandomStringUtils.randomAlphanumeric(size);
    }

    public static String randomNumeric(int size) {
        return RandomStringUtils.randomNumeric(size);
    }
}
