package com.greyslon.abi.exceptions;

public class ClientNotFoundException extends RuntimeException {

  public ClientNotFoundException() {
    super("client not found");
  }
}
