package com.freenow.blog.users;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class BlogUserQuestions {

  @Step("Get userId from response")
  public String getUserId(Response userDetailResp) {
    return String.valueOf(userDetailResp.getBody().jsonPath().getInt("[0].id"));
  }

  @Step("Get username from response")
  public String getUserName(Response userDetailResp) {
    return (userDetailResp.getBody().jsonPath().getString("[0].username"));
  }

  @Step("Validate username {0} present in response")
  public void validateUserName(String userName, Response lastResponse) {
    assertThat(getUserName(lastResponse)).isEqualToIgnoringCase(userName);
  }
}
