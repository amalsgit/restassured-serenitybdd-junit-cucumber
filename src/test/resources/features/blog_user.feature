Feature: Get user details
  As an admin user
  I should be able to get details of individual blog users

  Scenario: Should be able to get user details
    When I call the get user details endpoint for user "Samantha"
    Then user details should be retrieved
    And username should be "Samantha"

  Scenario: Empty response should be returned while searching for a non-existent user
    When I call the get user details endpoint for non-existent user
    Then empty response should be returned