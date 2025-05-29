Feature: Wishlist Page Access

  @TC-314
  Scenario: User can open the Wishlist page and see the correct title
    Given User is logged in (or logs in if needed)
    When User opens the Wishlist page
    Then The URL should be "https://demowebshop.tricentis.com/wishlist"
    And The Wishlist page title should be "Wishlist"