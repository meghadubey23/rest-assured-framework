package datadriven;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static Utilities.PropertiesUtility.getPropertyValue;

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



    @BeforeMethod
    public void initiateBaseUri(Method method) throws IOException {
        Test testClass = method.getAnnotation(Test.class);
        String group = testClass.groups()[0];
        this.baseUri = getPropertyValue(group.toLowerCase() + "." + "baseUri");
        requestSpecification = new RequestSpecBuilder().setBaseUri(baseUri).build();
        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }
}
