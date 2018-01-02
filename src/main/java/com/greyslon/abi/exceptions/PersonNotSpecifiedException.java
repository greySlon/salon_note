package com.greyslon.abi.exceptions;


public class PersonNotSpecifiedException extends RuntimeException {

  public PersonNotSpecifiedException() {
    super("client masterId is not specified");
  }
}
