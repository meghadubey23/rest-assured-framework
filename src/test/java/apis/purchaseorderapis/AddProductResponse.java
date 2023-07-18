package apis.purchaseorderapis;

import apis.BaseApi;

public class AddProductResponse extends BaseApi {

    private String productId;
    private String message;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
