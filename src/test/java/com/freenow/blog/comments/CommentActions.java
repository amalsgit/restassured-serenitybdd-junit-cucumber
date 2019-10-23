package com.freenow.blog.comments;

import com.freenow.blog.commontasks.CommonRequestSpec;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CommentActions {

  @Step("Get comments on post with postId {0}")
  public Response getPostComments(String postId) {
    return SerenityRest.given().spec(CommonRequestSpec.blogReqSpec())
        .basePath("comments")
        .queryParams("postId", postId)
        .get().then().extract().response();
  }
}
