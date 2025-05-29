#@Sample
#
#Feature: Basic HTML Form Testing
#  As a user
#  I want to fill out the basic HTML form
#  So that I can submit my information successfully
#
#  Background:
#    Given I navigate to the basic HTML form test page
#
#  @TC-01
#  Scenario Outline: Fill and submit form with different data
#    When I fill username with "<username>"
#    And I fill comments with "<comments>"
#    And I check the "<cb>" checkbox
#    And I click submit button
#    Then I should see "<username>" in results form
#
#    Examples:
#      | username | comments      | cb  |
#      | testuser | Test comment  | cb1 |
#      | admin    | Admin testing | cb3 |