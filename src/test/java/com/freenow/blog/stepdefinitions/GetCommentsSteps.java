package com.freenow.blog.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.freenow.blog.comments.CommentCalls;
import com.freenow.blog.comments.CommentQuestions;
import com.freenow.blog.commontasks.CommonQuestions;
import com.freenow.blog.commonutilities.ValidateEmail;
import com.freenow.blog.posts.BlogPostCalls;
import com.freenow.blog.posts.BlogPostQuestions;
import com.freenow.blog.users.UserCallQuestions;
import com.freenow.blog.users.UserCalls;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;

public class GetCommentsSteps {

  @Steps
  UserCalls userCalls;

  @Steps
  BlogPostCalls blogPostCalls;

  @Steps
  BlogPostQuestions blogPostQuestions;

  @Steps
  UserCallQuestions userCallQuestions;

  @Steps
  CommentCalls commentCalls;

  @Steps
  CommentQuestions commentQuestions;

  @Steps
  CommonQuestions commonQuestions;

  @Steps
  ValidateEmail validateEmail;

  @Given("a blog post by user \"(.*)\"")
  public void a_blog_post_by_user(String user) {
    userCalls.getUserDetails(user);
    commonQuestions.responseCodeIs(200, lastResponse());
    String userId = userCallQuestions.getUserId(lastResponse());
    Serenity.setSessionVariable("userId").to(userId);

    blogPostCalls.getUserPost(userId);
    String postId = blogPostQuestions.getPostId(lastResponse());
    Serenity.setSessionVariable("postId").to(postId);
  }

  @When("I call the endpoint to get comments for a post")
  public void i_call_the_endpoint_to_get_comments_for_a_post() {
    commentCalls.getPostComments(Serenity.sessionVariableCalled("postId"));
    commonQuestions.responseCodeIs(200, lastResponse());
  }

  @Then("all comments for the post should be returned")
  public void all_comments_for_the_post_should_be_returned() {
    assertThat(lastResponse().getBody().jsonPath().getList("").size()).isGreaterThan(1);
    commentQuestions.verifyCommentProperties(lastResponse());
  }

  @When("I call the endpoint to get comments for a non-existent post")
  public void i_call_the_endpoint_to_get_comments_for_a_non_existent_post() {
    commentCalls.getPostComments("999999");
  }

  @When("I retrieve comments for each post")
  public void retrieve_comments_for_each_post() {
    List posts = lastResponse().getBody().jsonPath().getList("");
    List<String> mailIds = new ArrayList<>();
    for (Object post : posts) {
      Map singlePost = (Map) post;
      String postId = String.valueOf(singlePost.get("id"));
      commentCalls.getPostComments(postId);
      List<String> mailId = lastResponse().getBody().jsonPath().getList("email");
      mailIds.addAll(mailId);
    }
    Serenity.setSessionVariable("mailIds").to(mailIds);
  }

  @Then("each comment should have an associated valid mailId")
  public void each_comment_should_have_an_associated_valid_mailId() {
    List<String> mailIds = Serenity.sessionVariableCalled("mailIds");
    SoftAssertions softly = new SoftAssertions();
    for (String mailid : mailIds) {
      softly.assertThat(validateEmail.isValid(mailid)).isTrue();
    }
    softly.assertAll();
  }
}
