package entity;

import apis.BaseApi;
import apis.googlemapsapis.AddLocResponse;

import java.util.ArrayList;
import java.util.List;

public class GoogleApiEntity extends BaseEntity {
    private final BaseApi actualResponse;
    private final BaseApi expectedResponse;

    public GoogleApiEntity(BaseApi actualResponse, BaseApi expectedResponse) {
        this.actualResponse = actualResponse;
        this.expectedResponse = expectedResponse;
    }

    public void verify() {
        AddLocResponse actual = (AddLocResponse) actualResponse;
        AddLocResponse expected = (AddLocResponse) expectedResponse;

        List<String[]> list = new ArrayList<>();

        String[] subList = new String[3];
        subList[0] = actual.getStatus();
        subList[1] = expected.getStatus();
        subList[2] = "Status";
        list.add(subList);

        subList = new String[3];
        subList[0] = actual.getStatus();
        subList[1] = expected.getStatus();
        subList[2] = "Scope";
        list.add(subList);

        verify(list);
    }
}
