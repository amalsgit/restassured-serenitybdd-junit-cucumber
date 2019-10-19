package com.freenow.blog.posts;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

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

  @Step("Verify the response body properties")
  public void verifyPostProperties(Response lastResponse) {
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].userId")).isNotNull();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].id")).isNotNull();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].title")).isNotNull();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].body")).isNotNull();
    softly.assertAll();
  }
}
