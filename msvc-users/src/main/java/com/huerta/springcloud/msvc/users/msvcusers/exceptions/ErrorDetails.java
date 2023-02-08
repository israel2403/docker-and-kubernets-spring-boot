package com.huerta.springcloud.msvc.users.msvcusers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorDetails {

  private HttpStatus httpStatus;

  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;

  private String message;

  private String debugMessage;
  private String details;
  private List<ApiValidationError> subErrors;


  public ErrorDetails() {
    this.timestamp = LocalDateTime.now();
  }

  public ErrorDetails(final HttpStatus httpStatus) {
    this();
    this.httpStatus = httpStatus;
  }

  public ErrorDetails(final HttpStatus httpStatus, final Throwable ex) {
    this();
    this.httpStatus = httpStatus;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
  }

  public ErrorDetails(
    final HttpStatus httpStatus,
    final String message,
    final Throwable ex
  ) {
    this();
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
  }
}
