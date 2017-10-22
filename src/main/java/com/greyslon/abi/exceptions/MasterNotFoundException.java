package com.greyslon.abi.exceptions;

public class MasterNotFoundException extends RuntimeException {

  public MasterNotFoundException() {
    super("the master is not found");
  }
}
