package com.huerta.springcloud.msvc.courses.msvccourses.exeptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustumizeResponseEntityExceptionHandler
  extends ResponseEntityExceptionHandler {

  public ResponseEntity<ErrorDetails> handleNotFoundException(
    final Exception ex,
    final WebRequest request
  ) {
    final ErrorDetails errorDetails = new ErrorDetails(
      LocalDateTime.now(),
      ex.getMessage(),
      request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<ErrorDetails> handleUUIDBadRequestException(
    final Exception ex,
    final WebRequest request
  ) {
    final ErrorDetails errorDetails = new ErrorDetails(
      LocalDateTime.now(),
      ex.getMessage(),
      request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(
      errorDetails,
      HttpStatus.BAD_REQUEST
    );
  }
}
