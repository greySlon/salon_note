package com.greyslon.abi.exceptions;

public class ProcedureNotFoundException extends RuntimeException {

  public ProcedureNotFoundException() {
    super("the procedure is not found");
  }
}
