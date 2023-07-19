Feature: Purchase Order Testing

  Scenario Outline: Placing an order
    Given Login to your account "<username>" and "<password>" "OrdersLoginApi"
    When Create a product "OrdersAddProductApi"
    Then Place an order for "<country>" "OrdersCreateProductApi"
    Then Get order details "after adding product" "<username>" "<productName>" "<country>" "<description>" "<orderPrice>" "OrdersGetDetailsApi"
    And Delete the product "OrdersDeleteProductApi"
    And Get order details "after adding product" "<username>" "<productName>" "<country>" "<description>" "<orderPrice>" "OrdersGetDetailsApi"
    Examples:
      | username              | password    | country | productName | description | orderPrice |
      | rahulshetty@gmail.com | Iamking@000 | India   | Denim       | wearables   | 11500      |
      | rahulshetty@gmail.com | Iamking@000 | US      | Shirt       | wearables   | 2000       |