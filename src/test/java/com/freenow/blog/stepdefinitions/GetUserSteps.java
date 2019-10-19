package com.freenow.blog.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.freenow.blog.commontasks.CommonQuestions;
import com.freenow.blog.users.UserCallQuestions;
import com.freenow.blog.users.UserCalls;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;


public class GetUserSteps {

  @Steps
  UserCalls userCalls;

  @Steps
  CommonQuestions commonQuestions;

  @Steps
  UserCallQuestions userCallQuestions;

  @When("I call the get user details endpoint for user \"(.*)\"")
  public void i_call_the_get_user_endpoint_for_user(String user) {
    userCalls.getUserDetails(user);
  }

  @When("I call the get user details endpoint for non-existent user")
  public void i_call_the_get_user_endpoint_for_non_existent_user() {
    userCalls.getUserDetails("amal");
  }

  @Then("user details should be retrieved")
  public void user_details_should_be_retrieved() {
    commonQuestions.responseCodeIs(200, lastResponse());
    userCallQuestions.verifyUserProperties(lastResponse());
  }

  @And("username should be \"(.*)\"")
  public void usernameShouldBe(String userName) {
    userCallQuestions.validateUserName(userName, lastResponse());
  }

  @Then("empty response should be returned")
  public void empty_response_should_be_returned() {
    commonQuestions.responseCodeIs(200, lastResponse());
    commonQuestions.responseShouldBeEmptyList(lastResponse());
  }
}
