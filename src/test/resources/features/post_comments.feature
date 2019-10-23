Feature: Get post comments
  As an admin
  I should be able to fetch comments of individual posts

  Scenario: "Get post comments" response schema should match with specification
    Given a blog post by user "Samantha"
    When I call the endpoint to get comments for a post
    And the schema should match with the specification defined in "post_comments.json"

  Scenario: Should be able to get comments for a post
    Given a blog post by user "Samantha"
    When I call the endpoint to get comments for a post
    Then all comments for the post should be returned

  Scenario: Emails in the comments should be in proper format
    Given a blog post by user "Samantha"
    When I retrieve comments for each post
    Then each comment should have an associated valid mailId

  Scenario: Empty response should be returned when searching for comments with non-existent postId
    When I call the endpoint to get comments for a non-existent post
    Then empty response should be returned
