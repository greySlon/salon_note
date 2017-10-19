package com.greyslon.abi.domain;

import static com.greyslon.abi.domain.ResponseKey.STATUS;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;

@Component
@RequestScope
public class Response extends HashMap<ResponseKey, Object> {

  public static final String STATUS_OK = "ok";
  public static final String STATUS_ERROR = "error";


  public Response() {
    put(STATUS, STATUS_OK);
  }
}
