package com.freenow.blog.commontasks;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

public class CommonRequestSpec {
  /**
   * Get Request Specification for blog endpoint
   *
   * @return RequestSpecification
   */
  public static RequestSpecification blogReqSpec() {
    EnvironmentVariables environmentVariables = Injectors.getInjector()
        .getInstance(EnvironmentVariables.class);

    String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
        .getProperty("baseurl");

    return new RequestSpecBuilder().setBaseUri(baseUrl)
        .setContentType("application/json")
        .build();
  }
}
