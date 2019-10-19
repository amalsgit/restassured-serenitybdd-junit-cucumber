package com.freenow.blog.comments;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

public class CommentQuestions {

  public static void verifyCommentProperties(Response lastResponse) {
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].postId")).isNotNull();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].id")).isNotNull();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].name")).isNotNull();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].email")).isNotNull();
    softly.assertThat(lastResponse.getBody().jsonPath().getString("[0].body")).isNotNull();
    softly.assertAll();
  }
}