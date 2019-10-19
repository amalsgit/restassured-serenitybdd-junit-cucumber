package com.freenow.blog.stepdefinitions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.freenow.blog.users.UserCalls;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class GetUserSteps {

  @When("I call the get user details endpoint for user \"(.*)\"")
  public void i_call_the_get_user_endpoint_for_user(String user) {
    UserCalls.getUserDetails(user);
  }

  @When("I call the get user details endpoint for non-existent user")
  public void i_call_the_get_user_endpoint_for_non_existent_user() {
    UserCalls.getUserDetails("amal");
  }

  @Then("user details should be retrieved")
  public void user_details_should_be_retrieved() {
    assertThat(lastResponse().statusCode()).as("Response code should be 200")
        .isEqualTo(200);
    assertThat(lastResponse().getBody().jsonPath().getList("id").get(0))
        .as("userid should be present")
        .isNotNull();
  }

  @And("username should be \"(.*)\"")
  public void usernameShouldBe(String userName) {
    assertThat(
        lastResponse().getBody().jsonPath().getList("username").get(0).toString())
        .as("User name should be equal to " + userName).isEqualToIgnoringCase(userName);
  }

  @Then("empty response should be returned")
  public void empty_response_should_be_returned() {
    assertThat(lastResponse().statusCode()).isEqualTo(200);
    assertThat(lastResponse().getBody().jsonPath().getList("").size()).isEqualTo(0);
  }
}
