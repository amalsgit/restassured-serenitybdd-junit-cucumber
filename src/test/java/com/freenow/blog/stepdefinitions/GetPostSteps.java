package com.freenow.blog.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.freenow.blog.commontasks.CommonQuestions;
import com.freenow.blog.posts.BlogPostCalls;
import com.freenow.blog.posts.BlogPostQuestions;
import com.freenow.blog.users.UserCallQuestions;
import com.freenow.blog.users.UserCalls;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class GetPostSteps {

  @Steps
  UserCalls userCalls;

  @Steps
  BlogPostQuestions blogPostQuestions;

  @Steps
  BlogPostCalls blogPostCalls;

  @Steps
  CommonQuestions commonQuestions;

  @Steps
  UserCallQuestions userCallQuestions;

  @Given("\"(.*)\" has few post to (?:.*) name in the blog")
  public void user_has_few_posts_in_the_blog(String user) {
    userCalls.getUserDetails(user);
    commonQuestions.responseCodeIs(200, lastResponse());

    int userId = lastResponse().getBody().jsonPath().getInt("[0].id");
    Serenity.setSessionVariable("userId").to(String.valueOf(userId));
  }

  @When("I call the endpoint to get posts by the user")
  public void i_call_the_endpoint_to_get_posts_by_the_user() {
    blogPostCalls.getUserPost(Serenity.sessionVariableCalled("userId"));
  }

  @Then("all posts by the user should be returned")
  public void all_posts_by_the_user_should_be_returned() {
    commonQuestions.responseCodeIs(200, lastResponse());

    List<Object> posts = lastResponse().jsonPath().getList("");
    blogPostQuestions.verifyUserIdInPosts(posts);
    blogPostQuestions.verifyPostProperties(lastResponse());
  }

  @When("I call the endpoint to get posts by the user with invalid user id")
  public void i_call_the_endpoint_to_get_posts_by_invalid_userId() {
    blogPostCalls.getUserPost("999999");
  }
}

