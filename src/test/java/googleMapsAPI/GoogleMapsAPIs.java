package googleMapsAPI;

import apis.googleMapsAPI.RequestBody;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoogleMapsAPIs {
    public static void main(String[] args) {

        //Add loc
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").body(RequestBody.postRequestBody())
                .when().put("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)").body("scope", equalTo("APP")).extract().asString(); //verify header and body here directly


        JsonPath js = new JsonPath(response);
        String place_id = js.getString("place_id");

        //get loc
        String getLocResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).body("location.latitude", equalTo("-38.383494")).extract().asString();

        //update loc
        String updateLocResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(RequestBody.putRequestBody(place_id))
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().asString();

        getLocResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).body("address", equalTo("Megha Dubey Address")).extract().asString();

        //delete loc
        String delLocResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(RequestBody.deleteReqBody(place_id))
                .when().put("maps/api/place/delete/json")
                .then().log().all().assertThat().statusCode(200).body("status", equalTo("OK")).extract().asString();

        js = new JsonPath(delLocResponse);
        Assert.assertEquals(js.get("status"), "OK");

    }
}