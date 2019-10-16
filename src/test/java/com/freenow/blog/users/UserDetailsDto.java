package com.freenow.blog.users;

import lombok.Data;

@Data
public class UserDetailsDto {
  private int id;
  private String name;
  private String email;
  private String address;

}
