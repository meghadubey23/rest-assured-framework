package stepDefinition;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeleteProductAndOrder")
    public void beforeScenario() throws IOException {        //execute this code only when place id is null
        //write a code that will give you place id

        PurchaseOrderStepDef m = new PurchaseOrderStepDef();
        if (PurchaseOrderStepDef.productId == null) {

            m.loginToYourAccountAnd("rahulshetty@gmail.com", "Iamking@000", "OrdersLoginApi");
            m.create_a_product("OrdersAddProductApi");
            m.place_an_order("India", "OrdersCreateProductApi");
        }
    }
}
