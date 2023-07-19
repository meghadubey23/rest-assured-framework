package apis.purchaseorderapis;

import apis.BaseApi;

public class CreateOrderResponse extends BaseApi {
    private String[] orders;
    private String[] productOrderId;
    private String message;

    public String[] getOrders() {
        return orders;
    }

    public void setOrders(String[] orders) {
        this.orders = orders;
    }

    public String[] getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String[] productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
