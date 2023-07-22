package entity;

import apis.BaseApi;
import apis.purchaseorderapis.*;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderEntity extends BaseEntity {
    private final BaseApi actualResponse;
    private final BaseApi expectedResponse;

    public PurchaseOrderEntity(BaseApi actualResponse, BaseApi expectedResponse) {
        this.actualResponse = actualResponse;
        this.expectedResponse = expectedResponse;
    }

    public void verifyLogin() {
        LoginResponse actual = (LoginResponse) actualResponse;
        LoginResponse expected = (LoginResponse) expectedResponse;

        List<String[]> list = new ArrayList<>();

        String[] subList = new String[3];
        subList[0] = actual.getMessage();
        subList[1] = expected.getMessage();
        subList[2] = "Success Message";
        list.add(subList);

        verify(list);
    }

    public void verifyAddProduct() {
        AddProductResponse actual = (AddProductResponse) actualResponse;
        AddProductResponse expected = (AddProductResponse) actualResponse;

        List<String[]> list = new ArrayList<>();

        String[] subList = new String[3];
        subList[0] = actual.getMessage();
        subList[1] = expected.getMessage();
        subList[2] = "Success Message";
        list.add(subList);

        verify(list);
    }

    public void verifyCreateOrder() {
        CreateOrderResponse actual = (CreateOrderResponse) actualResponse;
        CreateOrderResponse expected = (CreateOrderResponse) actualResponse;

        List<String[]> list = new ArrayList<>();

        String[] subList = new String[3];
        subList[0] = actual.getMessage();
        subList[1] = expected.getMessage();
        subList[2] = "Success Message";
        list.add(subList);

        subList = new String[3];
        subList[0] = actual.getMessage();
        subList[1] = expected.getMessage();
        subList[2] = "productOrderId";
        list.add(subList);

        verify(list);
    }

    public void getOrderDetails() {
        GetOrderDetailsResponse actual = (GetOrderDetailsResponse) actualResponse;
        GetOrderDetailsResponse expected = (GetOrderDetailsResponse) actualResponse;

        List<String[]> list = new ArrayList<>();

        String[] subList = new String[3];
        subList[0] = actual.getMessage();
        subList[1] = expected.getMessage();
        subList[2] = "Success Message";
        list.add(subList);

        if (actual.getData() != null) {
            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "_id";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "OrderById";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "orderBy";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "productOrderedId";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "productName";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "country";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "productDescription";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "orderPrice";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getMessage();
            subList[1] = expected.getMessage();
            subList[2] = "__v";
            list.add(subList);
        }

        verify(list);
    }

    public void deleteProductOrOrders() {
        DeleteApiResponse actual = (DeleteApiResponse) actualResponse;
        DeleteApiResponse expected = (DeleteApiResponse) expectedResponse;

        List<String[]> list = new ArrayList<>();

        String[] subList = new String[3];
        subList[0] = actual.getMessage();
        subList[1] = expected.getMessage();
        subList[2] = "Success Message";
        list.add(subList);

        verify(list);
    }
}
