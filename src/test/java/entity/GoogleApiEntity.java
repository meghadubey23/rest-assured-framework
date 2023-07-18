package entity;

import Utilities.AssertUtility;
import apis.googlemapsapis.AddLocResponse;

public class GoogleApiEntity {
    private AddLocResponse actual;
    private AddLocResponse expected;

    public GoogleApiEntity(AddLocResponse actual, AddLocResponse expected) {
        this.actual = actual;
        this.expected = expected;
    }

    public void verify() {
        AssertUtility.assertEquals(actual.getStatus(), expected.getStatus(), "Status");
        AssertUtility.assertEquals(actual.getScope(), expected.getScope(), "Scope");

    }
}
