package apis.purchaseorderapis;

public class CreateOrderRequest {

    private OrderValueRequest[] orders;

    public OrderValueRequest[] getOrders() {
        return orders;
    }

    public void setOrders(OrderValueRequest[] orders) {
        this.orders = orders;
    }
}
