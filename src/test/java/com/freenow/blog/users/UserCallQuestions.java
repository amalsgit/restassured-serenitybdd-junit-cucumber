package com.freenow.blog.users;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

public class UserCallQuestions {

  @Step("Get userId from response")
  public String getUserId(Response lastResponse) {
    return String.valueOf(lastResponse.getBody().jsonPath().getInt("[0].id"));
  }

  public void verifyUserProperties(Response lastResponse) {
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].id")).isNotEmpty();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].name")).isNotEmpty();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].username")).isNotEmpty();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].email")).isNotEmpty();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].email")).isNotEmpty();
    softly.assertAll();
  }

  @Step("Get username from response")
  public String getUserName(Response lastResponse) {
    return (lastResponse.getBody().jsonPath().getString("[0].username"));
  }

  @Step("Validate username {0} present in response")
  public void validateUserName(String userName, Response lastResponse) {
    assertThat(getUserName(lastResponse)).isEqualToIgnoringCase(userName);
  }
}
