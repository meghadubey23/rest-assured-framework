Feature: Purchase Order Testing

  Scenario: Placing an order
    Given Login to your account
    When Create a product
    Then Place an order
    Then Get order details
    And Delete the product