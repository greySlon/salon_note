package com.greyslon.abi.exceptions;

public class ApplicationException extends RuntimeException {

  private Object[] params;

  public ApplicationException(String message) {
    super(message);
  }

  public ApplicationException(String message, Object... params) {
    super(message);
    this.params = params;
  }

  public Object[] getParams() {
    return params;
  }
}
