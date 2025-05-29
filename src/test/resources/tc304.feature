Feature: View multiple items in Wish List

  Background: User is not logged-in and wishlist is empty

  @TC-304
  Scenario: Clear all items from the Wishlist and log out
    Given User is logged in (or logs in if needed)
    And User is on the Wishlist page
    When User selects all products in the Wishlist
    And User clicks the "Update wishlist" button
    Then Wishlist should be empty after update
    And User logs out from Wishlist

  Scenario Outline: Add multiple products to Wishlist and verify content
    Given User is logged in (or logs in if needed)
    When User opens product "<product1>" and adds quantity <qty1> to the Wishlist
    And User opens product "<product2>" and adds quantity <qty2> to the Wishlist
    And The Wishlist page should contain:
      | Name                  |  Quantity |
      | <product1>            |  <qty1>   |
      | <product2>            |  <qty2>   |
    Examples:
      | product1              | qty1  | product2                    | qty2 |
      | Blue and green Sneaker| 1     | Black & White Diamond Heart | 4    |