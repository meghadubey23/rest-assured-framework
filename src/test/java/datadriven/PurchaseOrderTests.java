package datadriven;

import apis.purchaseorderapis.*;
import entity.PurchaseOrderEntity;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PurchaseOrderTests extends BaseTest {

    private String token;
    private String userId;
    private String productId;
    private String orderId;

    @Test(groups = "Orders")
    public void purchaseAccountLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("rahulshetty@gmail.com");
        loginRequest.setUserPassword("Iamking@000");

        LoginResponse actualResponse = given().relaxedHTTPSValidation().contentType(ContentType.JSON).spec(getRequestSpecification()).body(loginRequest)
                .when().post("/api/ecom/auth/login")//relaxedHTTPSValidation: to bypass SSL verification
                .then().spec(getResponseSpecification()).extract().response().as(LoginResponse.class);

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setMessage("Login Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyLogin();

        this.token = (actualResponse.getToken());
        this.userId = (actualResponse.getUserId());
    }

    @Test(groups = "Orders", dependsOnMethods = "purchaseAccountLogin")
    public void addProduct() {
        //FormDataTypeRequest Example
        AddProductResponse actualResponse = given().spec(getRequestSpecification()).contentType(ContentType.MULTIPART).header("Authorization", token).param("productName", "Denim")
                .param("productAddedBy", userId).param("productCategory", "fashion")
                .param("productSubCategory", "pants").param("productPrice", "11500")
                .param("productDescription", "wearables").param("productFor", "men")
                .multiPart("productImage", new File("src/test/resources/images/denim.png"))
                .when().post("/api/ecom/product/add-product")
                .then().spec(getResponseSpecification()).extract().response().as(AddProductResponse.class);

        this.productId = actualResponse.getProductId();

        AddProductResponse expectedResponse = new AddProductResponse();
        expectedResponse.setMessage("Product Added Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyAddProduct();
    }

    @Test(groups = "Orders", dependsOnMethods = {"purchaseAccountLogin", "addProduct"})
    public void createOrder() {
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        orderValueRequest.setCountry("India");
        orderValueRequest.setProductOrderedId(productId);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setOrders(new OrderValueRequest[]{orderValueRequest});

        CreateOrderResponse actualResponse = given().spec(getRequestSpecification()).contentType(ContentType.JSON).header("Authorization", token).body(request)
                .when().post("/api/ecom/order/create-order")
                .then().spec(getResponseSpecification()).extract().response().as(CreateOrderResponse.class);

        CreateOrderResponse expectedResponse = new CreateOrderResponse();
        expectedResponse.setProductOrderId(new String[]{productId});
        expectedResponse.setMessage("Order Placed Successfully");

        PurchaseOrderEntity entity = new PurchaseOrderEntity(actualResponse, expectedResponse);
        entity.verifyCreateOrder();
        this.orderId = actualResponse.getOrders()[0];
    }

    @Test(groups = "Orders", dependsOnMethods = {"purchaseAccountLogin", "addProduct", "createOrder"})
    public void getOrderDetails() {
        GetOrderDetailsResponse actualResponse = given().spec(getRequestSpecification()).header("Authorization", token).queryParam("id", orderId)
                .when().get("/api/ecom/order/get-orders-details")
                .then().spec(getResponseSpecification()).extract().response().as(GetOrderDetailsResponse.class);

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

    @Test(groups = "Orders", dependsOnMethods = {"purchaseAccountLogin", "addProduct", "createOrder", "getOrderDetails"})
    public void deleteOrder() {
        DeleteApiResponse actualResponse = given().spec(getRequestSpecification()).header("Authorization", token)
                .when().delete("/api/ecom/product/delete-product/" + productId)
                .then().spec(getResponseSpecification()).extract().response().as(DeleteApiResponse.class);
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
