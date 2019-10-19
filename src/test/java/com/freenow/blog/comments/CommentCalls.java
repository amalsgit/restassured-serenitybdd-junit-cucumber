package com.freenow.blog.comments;

import com.freenow.blog.commontasks.CommonRequestSpec;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class CommentCalls {

  public static Response getPostComments(String postId) {
    return SerenityRest.given().spec(CommonRequestSpec.blogReqSpec())
        .basePath("comments")
        .queryParams("postId", postId)
        .get().then().extract().response();
  }
}
