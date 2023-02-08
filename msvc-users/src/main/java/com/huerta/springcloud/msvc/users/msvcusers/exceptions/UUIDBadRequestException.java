package com.huerta.springcloud.msvc.users.msvcusers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UUIDBadRequestException extends RuntimeException {

  public UUIDBadRequestException(final String message) {
    super(message);
  }
}
