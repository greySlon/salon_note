package com.greyslon.abi.exceptions;

public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException() {
    super("client not found");
  }
}
