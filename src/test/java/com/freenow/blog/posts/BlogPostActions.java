package com.freenow.blog.posts;

import com.freenow.blog.commontasks.CommonRequestSpec;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BlogPostActions {

  @Step("Get blog posts for user with userid {0}")
  public Response getUserPost(String userId) {
    return SerenityRest.given().spec(CommonRequestSpec.blogReqSpec())
        .basePath("posts")
        .queryParams("userId", userId)
        .get().then().extract().response();
  }
}
