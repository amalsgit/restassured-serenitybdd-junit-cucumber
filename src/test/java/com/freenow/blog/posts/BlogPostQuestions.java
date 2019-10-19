package com.freenow.blog.posts;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;

public class BlogPostQuestions {

  public static String getPostId(Response lastResponse) {
    return lastResponse.getBody().jsonPath().get("[0].id").toString();
  }

  public static void verifyUserIdInPosts(List<Object> posts) {
    for (Object o : posts) {
      Map post = (Map) o;
      assertThat(post.get("userId").toString())
          .isEqualTo(Serenity.sessionVariableCalled("userId").toString());
    }
  }
}
