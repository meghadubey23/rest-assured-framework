package Utilities;

public enum ResourcesApi {

    OrdersLoginApi("/api/ecom/auth/login"),
    OrdersAddProductApi("/api/ecom/product/add-product"),
    OrdersCreateProductApi("/api/ecom/order/create-order"),
    OrdersGetDetailsApi("/api/ecom/order/get-orders-details"),
    OrdersDeleteProductApi("/api/ecom/product/delete-product/"),
    OrdersDeleteOrderApi("/api/ecom/order/delete-order/");

    private String resourceApi;

    ResourcesApi(String resourceApi) {
        this.resourceApi = resourceApi;
    }

    public String getResource() {
        return resourceApi;
    }
}