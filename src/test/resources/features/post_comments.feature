Feature: Get post comments
  As an admin
  I should be able to fetch comments of individual posts

  @run
  Scenario: Should be able to get comments for a post
    Given a blog post by user "Samantha"
    When I call the endpoint to get comments for a post
    Then all comments for the post should be returned
