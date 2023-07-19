package cucumber;

import apis.purchaseorderapis.*;
import entity.PurchaseOrderEntity;
import io.cucumber.java.Before;
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

public class PurchaseOrderScenarioDef {

    private String baseUri;
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private String token;
    private String userId;
    private String productId;
    private String orderId;

    @Before
    public void initiateBaseUri() throws FileNotFoundException {
        System.out.println("hi");
        PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
        this.baseUri = "https://rahulshettyacademy.com";
        requestSpecification = new RequestSpecBuilder().setBaseUri(baseUri).addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }

    @Given("Login to your account")
    public void login_to_your_account() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("rahulshetty@gmail.com");
        loginRequest.setUserPassword("Iamking@000");

        LoginResponse actualResponse = given().relaxedHTTPSValidation().contentType(ContentType.JSON).spec(requestSpecification).body(loginRequest)
                .when().post("/api/ecom/auth/login")//relaxedHTTPSValidation: to bypass SSL verification
                .then().spec(responseSpecification).extract().response().as(LoginResponse.class);

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setMessage("Login Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyLogin();

        this.token = (actualResponse.getToken());
        this.userId = (actualResponse.getUserId());
    }

    @When("Create a product")
    public void create_a_product() {
        //FormDataTypeRequest Example
        AddProductResponse actualResponse = given().spec(requestSpecification).contentType(ContentType.MULTIPART).header("Authorization", token).param("productName", "Denim")
                .param("productAddedBy", userId).param("productCategory", "fashion")
                .param("productSubCategory", "pants").param("productPrice", "11500")
                .param("productDescription", "wearables").param("productFor", "men")
                .multiPart("productImage", new File("src/test/resources/images/denim.png"))
                .when().post("/api/ecom/product/add-product")
                .then().spec(responseSpecification).extract().response().as(AddProductResponse.class);

        this.productId = actualResponse.getProductId();

        AddProductResponse expectedResponse = new AddProductResponse();
        expectedResponse.setMessage("Product Added Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyAddProduct();
    }

    @Then("Place an order")
    public void place_an_order() {
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        orderValueRequest.setCountry("India");
        orderValueRequest.setProductOrderedId(productId);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setOrders(new OrderValueRequest[]{orderValueRequest});

        CreateOrderResponse actualResponse = given().spec(requestSpecification).contentType(ContentType.JSON).header("Authorization", token).body(request)
                .when().post("/api/ecom/order/create-order")
                .then().spec(responseSpecification).extract().response().as(CreateOrderResponse.class);

        CreateOrderResponse expectedResponse = new CreateOrderResponse();
        expectedResponse.setProductOrderId(new String[]{productId});
        expectedResponse.setMessage("Order Placed Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyCreateOrder();
        this.orderId = actualResponse.getOrders()[0];
    }

    @Then("Get order details")
    public void getOrderDetails() {
        GetOrderDetailsResponse actualResponse = given().spec(requestSpecification).header("Authorization", token).queryParam("id", orderId)
                .when().get("/api/ecom/order/get-orders-details")
                .then().spec(responseSpecification).extract().response().as(GetOrderDetailsResponse.class);

        DataValuesResponse dataValuesResponse = new DataValuesResponse();
        dataValuesResponse.set_id(orderId);
        dataValuesResponse.setOrderById(userId);
        dataValuesResponse.setOrderBy("rahulshetty@gmail.com");
        dataValuesResponse.setProductOrderedId(productId);
        dataValuesResponse.setProductName("Denim");
        dataValuesResponse.setCountry("India");
        dataValuesResponse.setProductDescription("wearables");
        dataValuesResponse.setOrderPrice("11500");
        dataValuesResponse.set__v(0);

        GetOrderDetailsResponse expectedResponse = new GetOrderDetailsResponse();
        expectedResponse.setData(dataValuesResponse);
        expectedResponse.setMessage("Orders fetched for customer Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.getOrderDetails();
    }

    @Then("Delete the product")
    public void delete_the_product() {
        DeleteApiResponse actualResponse = given().spec(requestSpecification).header("Authorization", token)
                .when().delete("/api/ecom/product/delete-product/" + productId)
                .then().spec(responseSpecification).extract().response().as(DeleteApiResponse.class);
        /*
        * OR:
        * .pathParam("productId", productId)
                .when().delete("/api/ecom/product/delete-product/{productId}")*/

        DeleteApiResponse expectedResponse = new DeleteApiResponse();
        expectedResponse.setMessage("Product Deleted Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.deleteProduct();
    }
}
