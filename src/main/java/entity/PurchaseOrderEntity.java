package entity;

import Utilities.AssertUtility;
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

    public void verify(List<List<Object>> list) {
        for (List<Object> subList : list) {
            AssertUtility.assertEquals(subList.get(0), subList.get(1), subList.get(2));
        }
        System.out.println("\n");
    }

    public void verifyLogin() {
        LoginResponse actual = (LoginResponse) actualResponse;
        LoginResponse expected = (LoginResponse) expectedResponse;

        List<List<Object>> list = new ArrayList<>();

        List<Object> subList = new ArrayList<>();
        subList.add(actual.getMessage());
        subList.add(expected.getMessage());
        subList.add("Success Message");

        list.add(subList);
        verify(list);
    }

    public void verifyAddProduct() {
        AddProductResponse actual = (AddProductResponse) actualResponse;
        AddProductResponse expected = (AddProductResponse) actualResponse;

        List<List<Object>> list = new ArrayList<>();

        List<Object> subList = new ArrayList<>();
        subList.add(actual.getMessage());
        subList.add(expected.getMessage());
        subList.add("Success Message");

        list.add(subList);
        verify(list);
    }

    public void verifyCreateOrder() {
        CreateOrderResponse actual = (CreateOrderResponse) actualResponse;
        CreateOrderResponse expected = (CreateOrderResponse) actualResponse;

        List<List<Object>> list = new ArrayList<>();

        List<Object> subList = new ArrayList<>();
        subList.add(actual.getMessage());
        subList.add(expected.getMessage());
        subList.add("Success Message");
        list.add(subList);

        subList = new ArrayList<>();
        subList.add(actual.getProductOrderId()[0]);
        subList.add(expected.getProductOrderId()[0]);
        subList.add("productOrderId");
        list.add(subList);

        verify(list);
    }

    public void getOrderDetails() {
        GetOrderDetailsResponse actual = (GetOrderDetailsResponse) actualResponse;
        GetOrderDetailsResponse expected = (GetOrderDetailsResponse) actualResponse;

        List<List<Object>> list = new ArrayList<>();

        List<Object> subList = new ArrayList<>();
        subList.add(actual.getMessage());
        subList.add(expected.getMessage());
        subList.add("Success Message");
        list.add(subList);

        if (actual.getData() != null) {
            subList = new ArrayList<>();
            subList.add(actual.getData().get_id());
            subList.add(expected.getData().get_id());
            subList.add("_id");
            list.add(subList);

            subList = new ArrayList<>();
            subList.add(actual.getData().getOrderById());
            subList.add(expected.getData().getOrderById());
            subList.add("OrderById");
            list.add(subList);

            subList = new ArrayList<>();
            subList.add(actual.getData().getOrderBy());
            subList.add(expected.getData().getOrderBy());
            subList.add("orderBy");
            list.add(subList);

            subList = new ArrayList<>();
            subList.add(actual.getData().getProductOrderedId());
            subList.add(expected.getData().getProductOrderedId());
            subList.add("productOrderedId");
            list.add(subList);

            subList = new ArrayList<>();
            subList.add(actual.getData().getProductName());
            subList.add(expected.getData().getProductName());
            subList.add("productName");
            list.add(subList);

            subList = new ArrayList<>();
            subList.add(actual.getData().getCountry());
            subList.add(expected.getData().getCountry());
            subList.add("country");
            list.add(subList);

            subList = new ArrayList<>();
            subList.add(actual.getData().getProductDescription());
            subList.add(expected.getData().getProductDescription());
            subList.add("productDescription");
            list.add(subList);

            subList = new ArrayList<>();
            subList.add(actual.getData().getOrderPrice());
            subList.add(expected.getData().getOrderPrice());
            subList.add("orderPrice");
            list.add(subList);

            subList = new ArrayList<>();
            subList.add(actual.getData().get__v());
            subList.add(expected.getData().get__v());
            subList.add("__v");
            list.add(subList);
        }

        verify(list);
    }

    public void deleteProductOrOrders() {
        DeleteApiResponse actual = (DeleteApiResponse) actualResponse;
        DeleteApiResponse expected = (DeleteApiResponse) expectedResponse;

        List<List<Object>> list = new ArrayList<>();

        List<Object> subList = new ArrayList<>();
        subList.add(actual.getMessage());
        subList.add(expected.getMessage());
        subList.add("Success Message");

        list.add(subList);
        verify(list);
    }
}
