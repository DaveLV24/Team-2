Feature: Wishlist functionality
  @TC-308
  Scenario: Add item to wishlist and verify it appears
    Given User is logged in
    When User adds a product to the wishlist
    And User navigates to the wishlist page
    Then The wishlist should display the added product
    And The page should load without error

  @TC-309
  Scenario: Remove item from wishlist and verify update
    Given User is logged in
    When User adds a product to the wishlist
    And User navigates to the wishlist page
    When User removes an item from the wishlist
    Then The wishlist should be updated and not contain the removed item

    @TC-310
  Scenario: Remove multiple selected items from wishlist
    Given User is logged in
    And User adds the following items to the wishlist:
      | Smartphone  |
      | 50's Rockabilly Polka Dot Top JR Plus Size |
    When User opens the wishlist page
    And User selects the following items to remove:
      | Smartphone  |
      | 50's Rockabilly Polka Dot Top JR Plus Size |
    And User updates the wishlist
    Then The wishlist should be updated and not contain the removed item

  @TC-311
  Scenario: Add item to Cart from Wish List
    Given User is logged in
    And User adds the following items to the wishlist:
      | Smartphone |
    When User opens the wishlist page
    And User selects an item in wishlist to add to cart
    And User clicks the Add to Cart button
    Then The item should appear in the shopping cart

  @TC-312
  Scenario: Add Multiple Selected Items to Cart
    Given User is logged in
    And User adds the following items to the wishlist:
      | Smartphone |
      | Phone Cover |
    When User opens the wishlist page
    And User selects the following items to add to cart:
      | Smartphone |
      | Phone Cover |
    And User clicks the Add to Cart button from wishlist
    Then The selected items should appear in the shopping cart:
      | Smartphone |
      | Phone Cover |
