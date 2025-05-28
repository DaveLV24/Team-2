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
    And User navigates to the wishlist page
    When User removes an item from the wishlist
    Then The wishlist should be updated and not contain the removed item
