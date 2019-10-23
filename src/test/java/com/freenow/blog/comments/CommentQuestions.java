package com.freenow.blog.comments;


import static org.assertj.core.api.Assertions.assertThat;

import com.freenow.blog.commonutilities.ValidateEmail;
import io.restassured.response.Response;
import java.util.List;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;

public class CommentQuestions {

  @Steps
  ValidateEmail validateEmail;

  @Step("Verify number of comments")
  public void verifyNumberOfComments(int commentCount, int expectedCount) {
    assertThat(commentCount).isEqualTo(expectedCount);
  }

  @Step("Get count of comments")
  public int getCommentCount(Response commentsResp) {
    return commentsResp.getBody().jsonPath().getList("").size();
  }

  @Step("Get email id from comment")
  public List<String> getMailIdFromComment(Response commentsResp) {
    return commentsResp.getBody().jsonPath().getList("email");
  }

  @Step("Verify mailid format in comments")
  public void verifyMailIdInComments(List<String> mailIds) {
    SoftAssertions softly = new SoftAssertions();
    for (String mailid : mailIds) {
      softly.assertThat(validateEmail.isValid(mailid)).isTrue();
    }
    softly.assertAll();
  }
}
