package com.freenow.blog.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.freenow.blog.comments.CommentActions;
import com.freenow.blog.comments.CommentQuestions;
import com.freenow.blog.commontasks.CommonQuestions;
import com.freenow.blog.commonutilities.ValidateEmail;
import com.freenow.blog.posts.BlogPostActions;
import com.freenow.blog.posts.BlogPostQuestions;
import com.freenow.blog.users.BlogUserQuestions;
import com.freenow.blog.users.BlogUserActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class GetCommentsSteps {

  @Steps
  BlogUserActions blogUserActions;

  @Steps
  BlogPostActions blogPostActions;

  @Steps
  BlogPostQuestions blogPostQuestions;

  @Steps
  BlogUserQuestions blogUserQuestions;

  @Steps
  CommentActions commentActions;

  @Steps
  CommentQuestions commentQuestions;

  @Steps
  CommonQuestions commonQuestions;

  @Steps
  ValidateEmail validateEmail;

  @Given("a blog post by user \"(.*)\"")
  public void a_blog_post_by_user(String user) {
    blogUserActions.getUserDetails(user);
    commonQuestions.responseCodeIs(200, lastResponse());
    String userId = blogUserQuestions.getUserId(lastResponse());
    Serenity.setSessionVariable("userId").to(userId);

    blogPostActions.getUserPost(userId);
    String postId = blogPostQuestions.getPostId(lastResponse());
    Serenity.setSessionVariable("postId").to(postId);
  }

  @When("I call the endpoint to get comments for a post")
  public void i_call_the_endpoint_to_get_comments_for_a_post() {
    commentActions.getPostComments(Serenity.sessionVariableCalled("postId"));
    commonQuestions.responseCodeIs(200, lastResponse());
  }

  @Then("all comments for the post should be returned")
  public void all_comments_for_the_post_should_be_returned() {
    int commentCount = commentQuestions.getCommentCount(lastResponse());
    commentQuestions.verifyNumberOfComments(commentCount, 5);
  }

  @When("I call the endpoint to get comments for a non-existent post")
  public void i_call_the_endpoint_to_get_comments_for_a_non_existent_post() {
    commentActions.getPostComments("999999");
  }

  @When("I retrieve comments for each post")
  public void retrieve_comments_for_each_post() {
    List posts = lastResponse().getBody().jsonPath().getList("");
    List<String> mailIds = new ArrayList<>();
    for (Object post : posts) {
      Map singlePost = (Map) post;
      String postId = String.valueOf(singlePost.get("id"));
      commentActions.getPostComments(postId);
      List<String> mailId = commentQuestions.getMailIdFromComment(lastResponse());
      mailIds.addAll(mailId);
    }
    Serenity.setSessionVariable("mailIds").to(mailIds);
  }

  @Then("each comment should have an associated valid mailId")
  public void each_comment_should_have_an_associated_valid_mailId() {
    List<String> mailIds = Serenity.sessionVariableCalled("mailIds");
    commentQuestions.verifyMailIdInComments(mailIds);
  }
}
