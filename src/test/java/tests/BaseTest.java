package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

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
    public void initiateBaseUri(Method method) {
        Test testClass = method.getAnnotation(Test.class);
        switch (testClass.groups()[0]) {
            case "Orders":
                this.baseUri = "https://rahulshettyacademy.com";
                break;
        }
        requestSpecification = new RequestSpecBuilder().setBaseUri(baseUri).build();
        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }
}
