Feature: Purchase Order Testing

  Scenario Outline: Placing an order
    Given Login to your account "<username>" and "<password>" "<resourceUrl>"
    When Create a product
    Then Place an order
    Then Get order details "after adding product"
    And Delete the product
    And Get order details "after removing product"
    Examples:
      | username              | password    | resourceUrl          |
      | rahulshetty@gmail.com | Iamking@000 | /api/ecom/auth/login |