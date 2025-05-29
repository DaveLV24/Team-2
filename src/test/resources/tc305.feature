Feature: Wish List counter matches actual number of items

Background: User is not logged-in and wishlist is empty

  @TC-305
  Scenario: Clear all items from the Wishlist and log out
    Given User is logged in (or logs in if needed)
    And User is on the Wishlist page
    When User selects all products in the Wishlist
    And User clicks the "Update wishlist" button
    Then Wishlist should be empty after update
    And User logs out from Wishlist

  Scenario: Add 3 different products to Wish List and verify counter and contents
   When User opens product "Blue and green Sneaker" and adds quantity 1 to the Wishlist
   Then The Wishlist counter should increase by 1

   When User opens product "Black & White Diamond Heart" and adds quantity 1 to the Wishlist
   Then The Wishlist counter should increase by 1

   When User opens product "Smartphone" and adds quantity 1 to the Wishlist
   Then The Wishlist counter should increase by 1

   Then The Wishlist page should contain:
      | Name                        | Quantity |
      | Blue and green Sneaker      | 1        |
      | Black & White Diamond Heart | 1        |
      | Smartphone                  | 1        |
  And The Wishlist counter in the Top Menu should show "3"
