Feature: Wishlist functionality

  Scenario: Add item to wishlist and verify it appears
    Given User is logged in
    When User adds a product to the wishlist
    And User navigates to the wishlist page
    Then The wishlist should display the added product
    And The page should load without error
