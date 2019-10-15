Feature: Get users of the blog
  As an admin user
  I should be able to get details of individual blog users

  Scenario: Should be able to get a user details
    Given a user named Samantha
    When I call the get users endpoint
    Then user details should be retrieved