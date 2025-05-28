# Author: Anna Rozvadovska
Feature: checking the shopping cart functionality

  Background:
    Given user is on the homepage

  @TC-401
  Scenario: Adding one item to the cart
    Given shopping cart is empty
    When user adds one featured item to the shopping cart
    Then message "The product has been added to your shopping cart" is displayed
    And user navigates to the cart
    And product "14.1-inch Laptop" is visible in the cart
    And cart count is 1

  @TC-402
  Scenario Outline: Adding multiple same items to the shopping cart
    Given shopping cart is empty
    When user navigates to the product details page "https://demowebshop.tricentis.com/141-inch-laptop"
    And user inputs "<count>" as a quantity
    And user clicks "Add to cart" button with id "add-to-cart-button-31"
    Then message "The product has been added to your shopping cart" is displayed
    And user navigates to the cart
    And product "14.1-inch Laptop" is visible in the cart
    And product quantity is <count>
    And cart count is <count>
    Examples:
      | count |
      | 3     |
      | 10    |

    @TC-403 @TC-404
    Scenario Outline: Adding items to the cart with a negative quantity or string
      When user navigates to the product details page "https://demowebshop.tricentis.com/141-inch-laptop"
      And user inputs "<input>" as a quantity
      And user clicks "Add to cart" button with id "add-to-cart-button-31"
      Then message "Quantity should be positive" is displayed
      And shopping cart is empty
      Examples:
        | input |
        | 0     |
        | -1    |
        | aaa   |

    @TC-413
    Scenario: Adding two different items to the shopping cart
      When user navigates to the product details page "https://demowebshop.tricentis.com/141-inch-laptop"
      And user clicks "Add to cart" button with id "add-to-cart-button-31"
      And user navigates to the product details page "https://demowebshop.tricentis.com/computing-and-internet"
      And user clicks "Add to cart" button with id "add-to-cart-button-13"
      And user is on the homepage
      And user navigates to the cart
      Then products are present in the cart
      And cart count is 2

    @TC-414
    Scenario: Add item to the cart after filling the required fields
      When user navigates to the product details page "https://demowebshop.tricentis.com/build-your-own-computer"
      And user fills in all required fields
      And user clicks "Add to cart" button with id "add-to-cart-button-16"
      And user is on the homepage
      And user navigates to the cart
      Then product "Build your own computer" is visible in the cart
      And cart count is 1

    @TC-415
    Scenario: Add item to the cart without filling the required fields
      When user navigates to the product details page "https://demowebshop.tricentis.com/build-your-own-computer"
      And user clicks "Add to cart" button with id "add-to-cart-button-16"
      Then message "Please select HDD" is displayed
      And shopping cart is empty

