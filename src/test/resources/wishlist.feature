Feature: Wishlist functionality
  @TC-308
  Scenario: Add item to wishlist and verify it appears
    Given User is logged in
    When User adds a product to the wishlist
    And User navigates to the wishlist page
    Then The wishlist should display the added product


  @TC-309
  Scenario: Remove item from Wish List
    Given User is logged in
    When User adds a product to the wishlist
    And User navigates to the wishlist page
    Then The wishlist should display the added product
    And User selects the following items to remove:
      | Smartphone  |

  @TC-310
  Scenario: Remove multiple selected items from wishlist
    Given User is logged in
    And User adds the following items to the wishlist:
      | Smartphone |
      | 50's Rockabilly Polka Dot Top JR Plus Size |
    When User opens the wishlist page
    And User selects the following items to remove:
      | Smartphone |
      | 50's Rockabilly Polka Dot Top JR Plus Size |



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
      | Blue and green Sneaker |
    When User opens the wishlist page
    And User selects the following items to add to cart:
      | Smartphone |
      | Blue and green Sneaker |
    And User clicks the Add to Cart button from wishlist
    Then The selected items should appear in the shopping cart:
      | Smartphone |
      | Blue and green Sneaker |

  @TC-313
  Scenario: Verify Wishlist Persistence After Logout and Login
    Given User is logged in
    And User adds the following items to the wishlist:
      |Smartphone|
    When User logs out
    And User is logged in
    And User opens the wishlist page
    Then The wishlist should contain the following items:
      |Smartphone|



