package com.freenow.blog.commontasks;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class CommonQuestions {

  @Step("Verify that API response is {0}")
  public void responseCodeIs(int responseCode, Response lastResponse) {
    assertThat(lastResponse.statusCode()).isEqualTo(responseCode);
  }

  @Step("Verify that response is empty list")
  public void responseShouldBeEmptyList(Response lastResponse) {
    assertThat(lastResponse.getBody().jsonPath().getList("").size()).isEqualTo(0);
  }

  @Step("Verify response schema with {1}")
  public void verifyResponseSchema(Response lastResponse, String schemaJson) {
    lastResponse.then().body(matchesJsonSchemaInClasspath("schema/" + schemaJson));
  }
}
