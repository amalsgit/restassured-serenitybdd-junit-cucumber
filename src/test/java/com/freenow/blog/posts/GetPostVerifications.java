package com.freenow.blog.posts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;

public class GetPostVerifications {

  public static void verifyUserIdInPosts(List<Object> posts) {
    for (Object o : posts) {
      Map post = (Map) o;
      Serenity.recordReportData().withTitle("posts").andContents(post.get("userId").toString());
      assertThat(post.get("userId").toString())
          .isEqualTo(Serenity.sessionVariableCalled("userId").toString());
      Serenity.recordReportData().withTitle("posts").andContents(o.toString());
    }
  }
}
