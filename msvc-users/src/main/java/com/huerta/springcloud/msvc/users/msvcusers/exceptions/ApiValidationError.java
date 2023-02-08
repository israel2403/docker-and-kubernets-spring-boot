package com.huerta.springcloud.msvc.users.msvcusers.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiValidationError {

  private String object;
  private String field;
  private Object rejectedValue;
  private String message;
}
