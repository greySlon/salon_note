package com.greyslon.abi.exceptions;

public class WorkItemNotFoundException extends RuntimeException {

  public WorkItemNotFoundException() {
    super("workitem is not specified");
  }
}
