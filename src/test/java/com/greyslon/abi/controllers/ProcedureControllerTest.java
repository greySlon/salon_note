package com.greyslon.abi.controllers;

import com.greyslon.abi.models.dto.ProcedureDto;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class ProcedureControllerTest {

  private ProcedureDto dto;

  @Before
  public void init() {
    dto = new ProcedureDto();
    dto.name = "polish";
  }

  @Test
  public void add() throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.put("http://localhost:8080/salon/procedure/add", dto);
  }

  @Test
  public void getActual() throws Exception {
  }

  @Test
  public void getArchived() throws Exception {
  }

  @Test
  public void update() throws Exception {
  }

  @Test
  public void disable() throws Exception {
  }

  @Test
  public void enable() throws Exception {
  }

}