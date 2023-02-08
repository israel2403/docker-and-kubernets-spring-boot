package com.huerta.springcloud.msvc.users.msvcusers.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {

  private UUID id;
  private String name;
  private String email;
  private String password;
}
