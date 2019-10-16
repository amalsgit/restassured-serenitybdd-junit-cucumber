package com.freenow.blog.users;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class UserCalls {

  public static Response getUserDetails(String user) {
    return SerenityRest.given().spec(UserRequestSpec.blogReqSpec())
        .queryParam("username", user)
        .get().then().extract().response();
  }
}
