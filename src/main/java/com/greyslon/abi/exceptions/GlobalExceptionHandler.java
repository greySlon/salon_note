package com.greyslon.abi.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Map handler(ApplicationException ex) {
    Object[] params = ex.getParams();
    String message = messageSource.getMessage(ex.getMessage(), params, Locale.getDefault());
    return getErrorResponse(message);
  }

  private Map getErrorResponse(Object msg) {
    return Collections.singletonMap("error", msg);
  }
}
