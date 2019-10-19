package com.freenow.blog.users;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserCallQuestions {

  @Step("Get userId from response")
  public String getUserId() {
    return String.valueOf(SerenityRest.lastResponse().getBody().jsonPath().getInt("[0].id"));
  }
}
