package com.greyslon.abi.exceptions;

public class WorkItemNotSpecifiedException extends RuntimeException {

  public WorkItemNotSpecifiedException() {
    super("a workitem is not specified");
  }
}
