package stepDefinition;

import Utilities.ResourcesApi;
import apis.purchaseorderapis.*;
import entity.PurchaseOrderEntity;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class PurchaseOrderStepDef {

    private static String baseUri;
    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;
    public static String productId;
    private static String token;
    private static String userId;
    private static String orderId;

    @BeforeAll
    public static void initiateBaseUri() throws FileNotFoundException {
        PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
        baseUri = "https://rahulshettyacademy.com";
        requestSpecification = new RequestSpecBuilder().setBaseUri(baseUri).addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }

    private String getResource(String resourceApi) {
        return ResourcesApi.valueOf(resourceApi).getResource();
    }

    /*
     * Scenario Outline in the feature file is used when we want to parameterize the test data
     * Otherwise just use Scenario*/
    @Given("Login to your account {string} and {string} {string}")
    public void loginToYourAccountAnd(String username, String password, String resourceApi) {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail(username);
        loginRequest.setUserPassword(password);

        LoginResponse actualResponse = given().relaxedHTTPSValidation().contentType(ContentType.JSON).spec(requestSpecification).body(loginRequest)
                .when().post(getResource(resourceApi))//relaxedHTTPSValidation: to bypass SSL verification
                .then().spec(responseSpecification).extract().response().as(LoginResponse.class);

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setMessage("Login Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyLogin();

        this.token = (actualResponse.getToken());
        this.userId = (actualResponse.getUserId());
    }

    @When("Create a product {string}")
    public void create_a_product(String resourceApi) {
        //FormDataTypeRequest Example
        AddProductResponse actualResponse = given().spec(requestSpecification).contentType(ContentType.MULTIPART).header("Authorization", token).param("productName", "Denim")
                .param("productAddedBy", userId).param("productCategory", "fashion")
                .param("productSubCategory", "pants").param("productPrice", "11500")
                .param("productDescription", "wearables").param("productFor", "men")
                .multiPart("productImage", new File("src/test/resources/images/denim.png"))
                .when().post(getResource(resourceApi))
                .then().spec(responseSpecification).extract().response().as(AddProductResponse.class);

        this.productId = actualResponse.getProductId();

        AddProductResponse expectedResponse = new AddProductResponse();
        expectedResponse.setMessage("Product Added Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyAddProduct();
    }

    @Then("Place an order for {string} {string}")
    public void place_an_order(String country, String resourceApi) {
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        orderValueRequest.setCountry(country);
        orderValueRequest.setProductOrderedId(productId);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setOrders(new OrderValueRequest[]{orderValueRequest});

        CreateOrderResponse actualResponse = given().spec(requestSpecification).contentType(ContentType.JSON).header("Authorization", token).body(request)
                .when().post(getResource(resourceApi))
                .then().spec(responseSpecification).extract().response().as(CreateOrderResponse.class);

        CreateOrderResponse expectedResponse = new CreateOrderResponse();
        expectedResponse.setProductOrderId(new String[]{productId});
        expectedResponse.setMessage("Order Placed Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyCreateOrder();
        this.orderId = actualResponse.getOrders()[0];
    }

    @Then("Get order details {string} {string} {string} {string} {string} {string} {string}")
    public void getOrderDetails(String transactionType, String orderBy, String productName, String country, String description, String orderPrice, String resourceApi) {
        System.out.println("Fetching product details " + transactionType);
        GetOrderDetailsResponse actualResponse = given().spec(requestSpecification).header("Authorization", token).queryParam("id", orderId)
                .when().get(getResource(resourceApi))
                .then().spec(responseSpecification).extract().response().as(GetOrderDetailsResponse.class);

        DataValuesResponse dataValuesResponse = new DataValuesResponse();
        dataValuesResponse.set_id(orderId);
        dataValuesResponse.setOrderById(userId);
        dataValuesResponse.setOrderBy(orderBy);
        dataValuesResponse.setProductOrderedId(productId);
        dataValuesResponse.setProductName(productName);
        dataValuesResponse.setCountry(country);
        dataValuesResponse.setProductDescription(description);
        dataValuesResponse.setOrderPrice(orderPrice);
        dataValuesResponse.set__v(0);

        GetOrderDetailsResponse expectedResponse = new GetOrderDetailsResponse();
        if (!transactionType.contains("after deleting")) {
            expectedResponse.setData(dataValuesResponse);
            expectedResponse.setMessage("Orders fetched for customer Successfully");
        } else
            expectedResponse.setMessage("Order not found");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.getOrderDetails();
    }

    @Then("Delete the product {string}")
    public void delete_the_product(String resourceApi) {
        DeleteApiResponse actualResponse = given().spec(requestSpecification).header("Authorization", token)
                .when().delete(getResource(resourceApi) + productId)
                .then().spec(responseSpecification).extract().response().as(DeleteApiResponse.class);
        /*
        * OR:
        * .pathParam("productId", productId)
                .when().delete("/api/ecom/product/delete-product/{productId}")*/

        DeleteApiResponse expectedResponse = new DeleteApiResponse();
        expectedResponse.setMessage("Product Deleted Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.deleteProductOrOrders();
    }

    @Then("Delete the order {string}")
    public void delete_the_order(String resourceApi) {
        DeleteApiResponse actualResponse = given().spec(requestSpecification).header("Authorization", token)
                .when().delete(getResource(resourceApi) + orderId)
                .then().spec(responseSpecification).extract().response().as(DeleteApiResponse.class);
        /*
        * OR:
        * .pathParam("orderId", orderId)
                .when().delete("/api/ecom/order/delete-order/{orderId}")*/

        DeleteApiResponse expectedResponse = new DeleteApiResponse();
        expectedResponse.setMessage("Orders Deleted Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.deleteProductOrOrders();
    }
}
