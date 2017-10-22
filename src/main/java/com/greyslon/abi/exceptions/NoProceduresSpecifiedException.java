package com.greyslon.abi.exceptions;

public class NoProceduresSpecifiedException extends RuntimeException {

  public NoProceduresSpecifiedException() {
    super("no procedure is specified");
  }
}
