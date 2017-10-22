package com.greyslon.abi.exceptions;

public class ProcedureNotSpecifiedException extends RuntimeException {

  public ProcedureNotSpecifiedException() {
    super("a procedure is not specified");
  }
}
