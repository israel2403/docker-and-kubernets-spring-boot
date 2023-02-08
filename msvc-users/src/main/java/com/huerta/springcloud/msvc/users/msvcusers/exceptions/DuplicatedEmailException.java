package com.huerta.springcloud.msvc.users.msvcusers.exceptions;

public class DuplicatedEmailException extends RuntimeException {

  public DuplicatedEmailException(final String message) {
    super(message);
  }
}
