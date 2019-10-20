package com.freenow.blog.posts;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

public class BlogPostQuestions {

  @Step("Get a postId from the response")
  public String getPostId(Response lastResponse) {
    return lastResponse.getBody().jsonPath().get("[0].id").toString();
  }

  @Step("Verify userId for each post is matching the owner userId")
  public void verifyUserIdInPosts(List<Object> posts) {
    for (Object o : posts) {
      Map post = (Map) o;
      assertThat(post.get("userId").toString())
          .isEqualTo(Serenity.sessionVariableCalled("userId").toString());
    }
  }
}
