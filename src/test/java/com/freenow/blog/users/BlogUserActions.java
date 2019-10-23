package com.freenow.blog.users;

import com.freenow.blog.commontasks.CommonRequestSpec;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BlogUserActions {

  @Step("Get details for user {0}")
  public Response getUserDetails(String user) {
    return SerenityRest.given().spec(CommonRequestSpec.blogReqSpec())
        .basePath("users")
        .queryParam("username", user)
        .get().then().extract().response();
  }
}
