Feature: Shopping cart item quantity functionality and total price calculations
  Background:
    Given I am on test product page
    And Test item is added to shopping cart
    And I am on the shopping cart page

  @TC-409
  Scenario: Zero is inserted as item quantity
    When I input 0 as new quantity value
    And I click update shopping cart button
    Then Message can be seen: "Your Shopping Cart is empty!"
    And Shopping cart link value is 0
