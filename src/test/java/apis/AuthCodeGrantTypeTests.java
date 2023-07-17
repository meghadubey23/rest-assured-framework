package apis;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class AuthCodeGrantTypeTests {

    public static void main(String[] args) throws InterruptedException {

        //Run in debug mode. Hit actual url and after authenticating get new url each time and use here.
        String actualUrl = "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php";

        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AZEOvhWFpwxO4ahfxJ7gd4sTafe4y7Pk5XRm4qE3i5-pqS4m-2yINAUflsdsp1uhOWuImw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println("code=" + code);

        String response = given().urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("grant_type", "authorization_code")
                .queryParams("state", "verifyfjdss")
                .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");
        System.out.println("accessToken=" + accessToken);

        given().contentType("application/json").
                queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").then().log().all().extract().asString();
    }

}
