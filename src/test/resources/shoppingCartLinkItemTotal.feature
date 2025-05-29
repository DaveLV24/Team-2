Feature: Shopping cart link shows how many items are in it
  Background:
    Given I am on test product page

    @TC-406
    Scenario Outline: Link represents item quantity
      When I input <number> as new quantity value
      And I click add to cart button
      Then Shopping cart link value is <number>

      Examples:
      |number|
      |10    |
      |13    |
      |37    |
      |69    |