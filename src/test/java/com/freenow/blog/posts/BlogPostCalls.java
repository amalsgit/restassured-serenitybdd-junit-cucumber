package com.freenow.blog.posts;

import com.freenow.blog.commontasks.CommonRequestSpec;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class BlogPostCalls {

  public static Response getUserPost(String userId) {
    return SerenityRest.given().spec(CommonRequestSpec.blogReqSpec())
        .basePath("posts")
        .queryParams("userId", userId)
        .get().then().extract().response();
  }
}
