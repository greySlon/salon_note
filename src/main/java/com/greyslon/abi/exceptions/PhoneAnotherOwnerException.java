package com.greyslon.abi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PhoneAnotherOwnerException extends RuntimeException {

  public PhoneAnotherOwnerException() {
  }

  public PhoneAnotherOwnerException(String message) {
    super(String.format("phone %s belongs to another person", message));
  }
}
