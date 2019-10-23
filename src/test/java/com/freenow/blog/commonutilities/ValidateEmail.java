package com.freenow.blog.commonutilities;

import net.thucydides.core.annotations.Step;

public class ValidateEmail {

  @Step("Check if email id {0} is of valid format ")
  public boolean isValid(String email) {
    String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    return email.matches(regex);
  }
}
