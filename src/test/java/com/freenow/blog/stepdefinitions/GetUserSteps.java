package com.freenow.blog.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.freenow.blog.commontasks.CommonQuestions;
import com.freenow.blog.users.BlogUserQuestions;
import com.freenow.blog.users.BlogUserActions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;


public class GetUserSteps {

  @Steps
  BlogUserActions blogUserActions;

  @Steps
  CommonQuestions commonQuestions;

  @Steps
  BlogUserQuestions blogUserQuestions;

  @When("I call the get user details endpoint for user \"(.*)\"")
  public void i_call_the_get_user_endpoint_for_user(String user) {
    blogUserActions.getUserDetails(user);
  }

  @When("I call the get user details endpoint for non-existent user")
  public void i_call_the_get_user_endpoint_for_non_existent_user() {
    blogUserActions.getUserDetails("amal");
  }

  @Then("user details should be retrieved")
  public void user_details_should_be_retrieved() {
    commonQuestions.responseCodeIs(200, lastResponse());
  }

  @And("username should be \"(.*)\"")
  public void usernameShouldBe(String userName) {
    blogUserQuestions.validateUserName(userName, lastResponse());
  }

  @Then("empty response should be returned")
  public void empty_response_should_be_returned() {
    commonQuestions.responseCodeIs(200, lastResponse());
    commonQuestions.responseShouldBeEmptyList(lastResponse());
  }

  @And("the schema should match with the specification defined in \"(.*)\"")
  public void theSchemaShouldMatchWithTheSpecification(String spec) {
    commonQuestions.verifyResponseSchema(lastResponse(), spec);

  }
}
