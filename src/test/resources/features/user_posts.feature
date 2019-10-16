Feature: Get user blog posts
  As an admin
  I should be able to retrieve post created by users

  @run
  Scenario: Should be able to retrieve user posts
    Given "Samantha" has few post to her name in the blog
    When I call the endpoint to get posts by the user
    Then all posts by the user should be returned