package com.greyslon.abi.exceptions;

public class MasterNotSpecifiedException extends RuntimeException {

  public MasterNotSpecifiedException() {
    super("master is not specified");
  }
}
