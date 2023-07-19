package sample;

import apis.libraryapis.*;
import entity.LibraryApiEntity;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class AuthCodeGrantTypeTests {

    //@Test(description = "Deserializing Response of Get Courses Api")
    public void LibraryApiTests() {
        //        This test requires manual intervention hence the base uri is not parameterized
        WebAutomation webAutomation1 = new WebAutomation();
        webAutomation1.setCourseTitle("Selenium Webdriver Java");
        webAutomation1.setPrice("50");

        WebAutomation webAutomation2 = new WebAutomation();
        webAutomation2.setCourseTitle("Cypress");
        webAutomation2.setPrice("40");

        WebAutomation webAutomation3 = new WebAutomation();
        webAutomation3.setCourseTitle("Protractor");
        webAutomation3.setPrice("40");

        Api api1 = new Api();
        api1.setCourseTitle("Rest Assured Automation using Java");
        api1.setPrice("50");

        Api api2 = new Api();
        api2.setCourseTitle("SoapUI Webservices testing");
        api2.setPrice("40");

        Mobile mobile = new Mobile();
        mobile.setCourseTitle("Appium-Mobile Automation using Java");
        mobile.setPrice("50");

        Courses courses = new Courses();
        courses.setWebAutomation(Arrays.asList(webAutomation1, webAutomation2, webAutomation3));
        courses.setApi(Arrays.asList(api1, api2));
        courses.setMobile(Arrays.asList(mobile));

        GetCoursesPojoResponse getCoursesPojo = new GetCoursesPojoResponse();
        getCoursesPojo.setInstructor("RahulShetty");
        getCoursesPojo.setUrl("rahulshettycademy.com");
        getCoursesPojo.setServices("projectSupport");
        getCoursesPojo.setExpertise("Automation");
        getCoursesPojo.setCourses(courses);
        getCoursesPojo.setLinkedIn("https://www.linkedin.com/in/rahul-shetty-trainer/");

        /*
        String actualUrl = "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php";
        WebDriver driver = new ChromeDriver();
        driver.get(actualUrl);
        driver.findElement(By.id("identifierId")).sendKeys("meghaa.dubey@gmail.com");
        driver.findElement(By.xpath(".//div//span[text()='Next']")).click();
        driver.findElement(By.xpath(".//div/input[@aria-label='Enter your password']")).sendKeys("Niraj@123");
        driver.findElement(By.xpath(".//div//span[text()='Next']")).click();
        */

        //Step1: Login and get authorized
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AZEOvhV_mFgWBbVnVqGjvmlq-dskxTcQk2_5K4GU-KyprHL9VSGyatUFgaIA94MsfhbRxQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];

        String response = given().urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("grant_type", "authorization_code")
                .queryParams("state", "verifyfjdss")
                .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .when()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");

        //Step2: Get Courses > Deserializing Response
        GetCoursesPojoResponse gc = given().contentType("application/json").queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCoursesPojoResponse.class);

        LibraryApiEntity libraryApiEntity = new LibraryApiEntity(gc, getCoursesPojo);
        libraryApiEntity.verify();
    }
}
