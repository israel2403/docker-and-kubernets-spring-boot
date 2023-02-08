package com.huerta.springcloud.msvc.courses.msvccourses.feing.models.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {

  private UUID id;

  private String name;

  private String email;

  private String password;
}
