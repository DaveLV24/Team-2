Feature: Shopping cart item quantity functionality and total price calculations
  Background:
    Given I am on test product page
    And Test item is added to shopping cart
    And I am on the shopping cart page

  @TC-407
  Scenario Outline: Increase item quantity
    When I input <number> as new quantity value
    And I click update shopping cart button
    Then Quantity of item is bigger than previous and equals <number>
    And Total price is calculated correctly
    And Shopping cart link value is <number>

    #Examples should be bigger than initial quantity of test item
    Examples:
    |number|
    |5     |
    |25    |
    |7     |
    |13    |

  @TC-409
  Scenario: Zero is inserted as item quantity
    When I input 0 as new quantity value
    And I click update shopping cart button
    Then Message can be seen: "Your Shopping Cart is empty!"
    And Shopping cart link value is 0
