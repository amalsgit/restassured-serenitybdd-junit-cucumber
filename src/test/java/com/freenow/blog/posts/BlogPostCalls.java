package com.freenow.blog.posts;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class BlogPostCalls {

  public static Response getUserPost(String userId) {
    return SerenityRest.given().spec(BlogPostRequestSpec.blogReqSpec())
        .basePath("posts")
        .queryParams("userId",userId)
        .get().then().extract().response();
  }
}
