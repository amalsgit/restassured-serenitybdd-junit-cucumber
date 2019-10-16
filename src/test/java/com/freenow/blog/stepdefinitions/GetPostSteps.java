package com.freenow.blog.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.freenow.blog.posts.BlogPostCalls;
import com.freenow.blog.posts.GetPostVerifications;
import com.freenow.blog.users.UserCalls;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import java.util.List;
import net.serenitybdd.core.Serenity;

public class GetPostSteps {

  Response response;

  @Given("\"(.*)\" has few post to (?:.*) name in the blog")
  public void user_has_few_posts_in_the_blog(String user) {
    response = UserCalls.getUserDetails(user);
    assertThat(response.statusCode()).as("Response code should be 200").isEqualTo(200);
    int userId = response.getBody().jsonPath().getInt("[0].id");
    Serenity.setSessionVariable("userId").to(String.valueOf(userId));
  }

  @When("I call the endpoint to get posts by the user")
  public void i_call_the_endpoint_to_get_posts_by_the_user() {
    response = BlogPostCalls.getUserPost(Serenity.sessionVariableCalled("userId"));
  }

  @Then("all posts by the user should be returned")
  public void all_posts_by_the_user_should_be_returned() {
    assertThat(response.statusCode()).as("Response code should be 200").isEqualTo(200);
    assertThat(response.getBody().jsonPath().getInt("[0].userId"))
        .isEqualTo(Integer.valueOf(Serenity.sessionVariableCalled("userId")));
    List<Object> posts = response.jsonPath().getList("");
    GetPostVerifications.verifyUserIdInPosts(posts);
  }
}

