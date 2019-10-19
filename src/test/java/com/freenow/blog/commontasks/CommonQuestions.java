package com.freenow.blog.commontasks;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class CommonQuestions {

  @Step("Verify that API response is {0}")
  public void responseCodeIs(int responseCode, Response lastResponse) {
    assertThat(lastResponse.statusCode()).isEqualTo(responseCode);
  }
}