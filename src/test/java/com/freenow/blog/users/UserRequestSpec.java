package com.freenow.blog.users;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

public class UserRequestSpec {

  /**
   * Get Request Specification for get user calls
   *
   * @return UserRequestSpec
   */
  public static RequestSpecification blogReqSpec() {
    EnvironmentVariables environmentVariables = Injectors.getInjector()
        .getInstance(EnvironmentVariables.class);

    String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
        .getProperty("baseurl");

    return new RequestSpecBuilder().setBaseUri(baseUrl).setBasePath("users")
        .setContentType("application/json")
        .build();
  }
}
