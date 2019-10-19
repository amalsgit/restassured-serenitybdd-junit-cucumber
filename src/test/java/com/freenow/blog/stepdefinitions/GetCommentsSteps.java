package com.freenow.blog.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.freenow.blog.comments.CommentCalls;
import com.freenow.blog.comments.CommentQuestions;
import com.freenow.blog.posts.BlogPostCalls;
import com.freenow.blog.posts.BlogPostQuestions;
import com.freenow.blog.users.UserCallQuestions;
import com.freenow.blog.users.UserCalls;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;

public class GetCommentsSteps {

  @Given("a blog post by user \"(.*)\"")
  public void a_blog_post_by_user(String user) {
    UserCalls.getUserDetails(user);
    assertThat(lastResponse().statusCode()).isEqualTo(200);
    String userId = UserCallQuestions.getUserId();

    BlogPostCalls.getUserPost(userId);
    String postId= BlogPostQuestions.getPostId(lastResponse());
    Serenity.setSessionVariable("postId").to(postId);
  }

  @When("I call the endpoint to get comments for a post")
  public void i_call_the_endpoint_to_get_comments_for_a_post() {
    CommentCalls.getPostComments(Serenity.sessionVariableCalled("postId"));
    assertThat(lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Then("all comments for the post should be returned")
  public void all_comments_for_the_post_should_be_returned() {
    assertThat(lastResponse().getBody().jsonPath().getList("").size()).isGreaterThan(1);
    CommentQuestions.verifyCommentProperties(lastResponse());
  }
}