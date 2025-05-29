Feature: Shopping cart when multiple items are added quantity functionality and total price calculations
  Background:
    Given Multiple test products with given names and quantities are added to cart:
    #Product link url ending and quantity
    |smartphone     |6|
    |141-inch-laptop|4|
    |fiction        |8|
    And I am on the shopping cart page

    @TC-410
    Scenario: Increase all item quantities
      #Inputted quantities should be bigger than initial values
      When I input new quantity values for products in cart:
      |13|
      |37|
      |69|
      And I click update shopping cart button
      Then Quantities of items are bigger than initial values:
      |6|
      |4|
      |8|
      And Total with multiple values is calculated correctly
      And Shopping cart link value equals sum of all quantities

    @TC-411
    Scenario: Decrease all item quantities
      #Inputted quantities should be smaller than initial values
      When I input new quantity values for products in cart:
        |3|
        |2|
        |1|
      And I click update shopping cart button
      Then Quantities of items are smaller than initial values:
        |6|
        |4|
        |8|
      And Total with multiple values is calculated correctly
      And Shopping cart link value equals sum of all quantities
      
    @TC-414
    Scenario: Removing all items from shopping cart
      When I click remove checkbox beside each the product
      And I click update shopping cart button
      Then Message can be seen: "Your Shopping Cart is empty!"
      And Shopping cart link value is 0

