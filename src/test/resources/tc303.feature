Feature: Add product to Wish List from Product Details page

  @TC-303
  Scenario: Add product to Wish List and verify counter update
    Given User is logged in (or logs in if needed)
    And User remembers the current Wishlist count
    When User is on a "Blue and green Sneaker" page
    And User clicks the "Add to Wish List" button
    Then The Wishlist counter should increase by 1

