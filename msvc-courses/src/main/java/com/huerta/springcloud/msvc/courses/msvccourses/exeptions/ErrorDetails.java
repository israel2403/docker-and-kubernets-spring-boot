package com.huerta.springcloud.msvc.courses.msvccourses.exeptions;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDetails {

  private LocalDateTime timestatp;
  private String message;
  private String details;

  public ErrorDetails(LocalDateTime timestatp, String message, String details) {
    this.timestatp = timestatp;
    this.message = message;
    this.details = details;
  }
}
