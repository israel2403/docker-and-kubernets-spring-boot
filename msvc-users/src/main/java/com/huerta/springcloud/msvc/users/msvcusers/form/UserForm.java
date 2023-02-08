package com.huerta.springcloud.msvc.users.msvcusers.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class UserForm {

  @NotBlank(message = "The name property must have a value")
  private String name;

  @NotBlank(message = "The email property must have a value")
  @Email(message = "The formmat of this email is not correct")
  private String email;

  @NotBlank(message = "The password property must have a value")
  private String password;
}
