Feature: Purchase Order Testing

  @AddProductAndOrder
  Scenario Outline: Adding a Product and Placing an order
    Given Login to your account "<username>" and "<password>" "OrdersLoginApi"
    When Create a product "OrdersAddProductApi"
    Then Place an order for "<country>" "OrdersCreateProductApi"

    Examples:
      | username              | password    | country | productName | description | orderPrice |
      | rahulshetty@gmail.com | Iamking@000 | India   | Denim       | wearables   | 11500      |
#      | rahulshetty@gmail.com | Iamking@000 | US      | Shirt       | wearables   | 2000       |

  @DeleteProductAndOrder
  Scenario: Deleting an existing product and order
    Given Login to your account "rahulshetty@gmail.com" and "Iamking@000" "OrdersLoginApi"
    When Get order details "before deleting the product and the order" "rahulshetty@gmail.com" "Denim" "India" "wearables" "11500" "OrdersGetDetailsApi"
    And Delete the product "OrdersDeleteProductApi"
    Then Delete the order "OrdersDeleteOrderApi"
    And Get order details "after deleting the product and the order and the order" "" "" "" "" "" "OrdersGetDetailsApi"
