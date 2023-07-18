package apis.purchaseorderapis;

import apis.BaseApi;

public class GetOrderDetailsResponse extends BaseApi {
    private DataValuesResponse data;
    private String message;

    public DataValuesResponse getData() {
        return data;
    }

    public void setData(DataValuesResponse data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
