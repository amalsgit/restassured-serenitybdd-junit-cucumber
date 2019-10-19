package com.freenow.blog.users;

import net.serenitybdd.rest.SerenityRest;

public class UserCallQuestions {

  public static String getUserId() {
    return String.valueOf(SerenityRest.lastResponse().getBody().jsonPath().getInt("[0].id"));
  }
}
