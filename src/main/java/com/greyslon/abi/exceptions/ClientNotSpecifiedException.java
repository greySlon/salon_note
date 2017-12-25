package com.greyslon.abi.exceptions;


public class ClientNotSpecifiedException extends RuntimeException {

  public ClientNotSpecifiedException() {
    super("client id is not specified");
  }
}
