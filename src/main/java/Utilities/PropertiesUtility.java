package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

    public static String getPropertyValue(String propertyKey) throws IOException {
        String resourceDirectory = System.getProperty("user.dir") + "/src/test/resources/";

        FileInputStream file = new FileInputStream(resourceDirectory + "test.properties");

        Properties properties = new Properties();
        properties.load(file);

        return properties.getProperty(propertyKey);
    }
}
