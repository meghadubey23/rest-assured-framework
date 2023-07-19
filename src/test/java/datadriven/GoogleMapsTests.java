package datadriven;

import apis.googlemapsapis.AddLocResponse;
import apis.googlemapsapis.GoogleLocation;
import apis.googlemapsapis.GooglePojoClass;
import entity.GoogleApiEntity;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class GoogleMapsTests extends BaseTest {

    /*
     * Serialization = In RestAssured is a process of converting a Java object into a Request Body(Json)
     * Deserialization = In RestAssured is a process of converting back a Response Body(Json) to a Java object
     * Create POJO class(data class)
     * Create Test class to create an object of POJO class
     * RestAssured to trigger request using Serialization And Deserialization*/

    @Test(description = "Serializing Request and Deserializing Response of Post Add Google Loc", groups = "GoogleMaps")
    public void googleAddLocation() {
        GoogleLocation googleLocation = new GoogleLocation();
        googleLocation.setLat(-34.427362);
        googleLocation.setLng(35.427362);

        GooglePojoClass googlePojoClass = new GooglePojoClass();
        googlePojoClass.setLocation(googleLocation);
        googlePojoClass.setAccuracy(50);
        googlePojoClass.setName("Frontline house123");
        googlePojoClass.setPhone_number("(+91) 983 893 3937");
        googlePojoClass.setAddress("29, side layout, cohen 09");

        googlePojoClass.setTypes(new String[]{"test1", "test2"});

        googlePojoClass.setWebsite("http://google.com");
        googlePojoClass.setLanguage("French-IN");

        AddLocResponse expected = new AddLocResponse();
        expected.setStatus("OK");
        expected.setScope("APP");

        /*Breaking this request response using Spec Builders*/
        //given().spec(requestSpecification).body(googlePojoClass).when().post("/maps/api/place/add/json").then().spec(responseSpecification).extract().response().as(AddLocResponse.class);

        AddLocResponse actualResponse = given().spec(getRequestSpecification()).body(googlePojoClass)
                .when().post("/maps/api/place/add/json")
                .then().spec(getResponseSpecification()).extract().response().as(AddLocResponse.class);

        GoogleApiEntity googleApiEntity = new GoogleApiEntity(actualResponse, expected);
        googleApiEntity.verify();
    }
}