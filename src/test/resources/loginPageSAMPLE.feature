#@Sample
#
#Feature: SAMPLE Login to HerokuApp
#
#  Scenario: SAMPLE Successful login with valid credentials
#    Given the user is on the login page
#    When the user logs in with username "tomsmith" and password "SuperSecretPassword!"
#    Then the user should see a success message
#
#  @Negative
#  Scenario: SAMPLE Failed login with invalid credentials
#    Given the user is on the login page
#    When the user logs in with username "invalidUser" and password "wrongPassword"
#    Then the user should see an error message