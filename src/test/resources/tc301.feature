Feature: Check visibility of Wish List link and item counter in Top Menu

  @TC-301
  Scenario: Clear all items from the Wishlist and log out
    Given User is logged in (or logs in if needed)
    And User is on the Wishlist page
    When User selects all products in the Wishlist
    And User clicks the "Update wishlist" button
    Then Wishlist should be empty after update
    And User logs out from Wishlist

  Scenario: Check visibility of Wish List link and item counter in Top Menu
    Given User is logged in (or logs in if needed)
    And No items are added to the Wishlist
    When User opens the homepage
    Then User should see the "Wishlist" link in the Top Menu
    And The Wishlist counter should show "0"