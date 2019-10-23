# Java-Serenity-RestAssured-Cucumber-JUnit-Maven Sample API Automation Solution
[![CircleCI](https://circleci.com/gh/amalsgit/restassured-serenitybdd-junit-cucumber/tree/master.svg?style=svg)](https://circleci.com/gh/amalsgit/restassured-serenitybdd-junit-cucumber/tree/master)

## Introduction

This is a sample Rest API test solution for sample endpoints available in https://jsonplaceholder.typicode.com/. The published APIs represent a blog application where users can publish post and comment on them.

Tests are written using a combination of SerenityBDD, RestAssured, Cucumber, Junit & Maven.

## Framework & Design Considerations
- Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.
- API calls & validations are made using RestAssured and SerenityRest which is a wrapper on top of RestAssured. 
- Tests are written in BDD Gherkin format in Cucumber feature files and it is represented as a living documentation in the test report. 
- This solution is designed in an Action-Question pattern with the code base categorized into domain model packages based on user actions and questions to understand/validate results. 
- Each domain package consist of an Action class where API actions are defined and another Question class where user questions/assertions are written.
- These domain models are called from a step-definitions class which are in-turn called from BDD tests.
- A test scenario to validate API response schema has been included for each endpoint in the respective feature file. The API spec for schema comparison is placed inside "schema" folder in test resources. The specs are generated from https://www.liquid-technologies.com/online-json-to-schema-converter.

### The project directory structure

```Gherkin
src
  + test
    + java                          Test runners and supporting code
      + posts                       Domain model package consisting of all actions/questions on blog posts functionality
          BlogPostActions           API calls/User actions on blog post APIs
          BlogPostQuestions         User questions/validations on blog post API response
      + commontasks                 Package for all common actions and questions
          CommonQuestions           All common questions/validations across all the domain models
          CommonRequestSpec         Common Request Spec for the API calls
      + stepdefinitions             Step definitions for the BDD feature
      + commonutilies               Common utility methods
    + resources
      + features                    Feature files directory
          post_comments.feature     Feature containing BDD scenarios
      + schema                      Folder containing json schema for API schema validation
      Serenity.conf                 Configurations file

```
## Executing the tests
Run `mvn clean verify` from the command line.

The test results will be recorded here `target/site/serenity/index.html`.
Please run the below command from root directory to open the result after execution.
```bash
open target/site/serenity/index.html 
```
Reports can be seen in circleci, under the artifacts section `serenity/index.html`.
Here is the direct link to one of the build artifact: https://18-215099279-gh.circle-artifacts.com/0/home/circleci/restassured-serenitybdd-junit-cucumber/target/site/serenity/index.html.

The report records the API calls and its response in a very readable format as shown below.
![Sample Report Image](https://user-images.githubusercontent.com/22020329/67158844-02705300-f35b-11e9-8147-51b1137dcec8.png)

Each step in tests are very clearly documented for readability and debugging in case of failures.

![Sample Report Steps](https://user-images.githubusercontent.com/22020329/67158871-63982680-f35b-11e9-82ae-b5e6422167d3.png)


### Additional configurations

Additional command line parameters can be passed for switching the application environment.
```json
$ mvn clean verify -Denvironment=dev
```
Configurations to for different environments are set in the `test/resources/serenity.conf` file. In real time projects each environment can be configured with its baseurl to run the tests based on different environments.
```
environments {
  default {
    baseurl = "https://jsonplaceholder.typicode.com"
  }
  dev {
    baseurl = "https://jsonplaceholder.typicode.com"
  }
  staging {
    baseurl = "https://jsonplaceholder.typicode.com"
  }
}
```
