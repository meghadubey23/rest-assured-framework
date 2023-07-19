package datadriven.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {

    private String baseUri;
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }

    private static String getPropertyValue(String propertyKey) throws IOException {
        String resourceDirectory = System.getProperty("user.dir") + "/src/test/resources/";

        FileInputStream file = new FileInputStream(resourceDirectory + "test.properties");

        Properties properties = new Properties();
        properties.load(file);

        return properties.getProperty(propertyKey);
    }

    @BeforeMethod
    public void initiateBaseUri(Method method) throws IOException {
        Test testClass = method.getAnnotation(Test.class);
        String group = testClass.groups()[0];
        switch (group) {
            case "Orders":
                this.baseUri = getPropertyValue(group.toLowerCase() + "." + "baseUri");
                break;
        }
        requestSpecification = new RequestSpecBuilder().setBaseUri(baseUri).build();
        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }
}
