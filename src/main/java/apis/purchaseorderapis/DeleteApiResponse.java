package apis.purchaseorderapis;

import apis.BaseApi;

public class DeleteApiResponse extends BaseApi {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
