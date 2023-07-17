package apis.entity;

import apis.googlemapsapis.AddLocResponse;
import org.testng.Assert;

public class GoogleApiEntity {
    private AddLocResponse actual;
    private AddLocResponse expected;

    public GoogleApiEntity(AddLocResponse actual, AddLocResponse expected) {
        this.actual = actual;
        this.expected = expected;
    }

    public void verify() {
        Assert.assertEquals(actual.getStatus(), expected.getStatus());
        Assert.assertEquals(actual.getScope(), expected.getScope());

    }
}
