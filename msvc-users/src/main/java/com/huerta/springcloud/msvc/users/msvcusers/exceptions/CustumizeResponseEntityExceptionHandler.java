package com.huerta.springcloud.msvc.users.msvcusers.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustumizeResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetails> handleInvalidArgument(
    MethodArgumentNotValidException ex
  ) {
    final List<ApiValidationError> apiSubErrors = ex
      .getBindingResult()
      .getFieldErrors()
      .stream()
      .map(CustumizeResponseEntityExceptionHandler::fromFiledErrorToApiSubError)
      .collect(Collectors.toList());
    final ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST);
    errorDetails.setMessage("Property format are incorret");
    errorDetails.setSubErrors(apiSubErrors);
    return new ResponseEntity<ErrorDetails>(
      errorDetails,
      HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDetails> handleNotFoundException(
    final Exception ex,
    final WebRequest request
  ) {
    final ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND);
    errorDetails.setMessage(ex.getMessage());
    request.getDescription(false);
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicatedEmailException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDetails handleDuplicatedEmailException(
    final DuplicatedEmailException ex,
    final WebRequest request
  ) {
    final ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST);
    errorDetails.setMessage(ex.getMessage());
    return errorDetails;
  }

  private static ApiValidationError fromFiledErrorToApiSubError(
    final FieldError fieldError
  ) {
    return ApiValidationError
      .builder()
      .object(fieldError.getObjectName())
      .field(fieldError.getField())
      .rejectedValue(fieldError.getRejectedValue())
      .message(fieldError.getDefaultMessage())
      .build();
  }
}
