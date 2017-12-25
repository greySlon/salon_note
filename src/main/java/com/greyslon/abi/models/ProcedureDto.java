package com.greyslon.abi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcedureDto {

  @JsonProperty(value = "id")
  public Long id;

  @JsonProperty(value = "procedure_name")
  public String name;

  public ProcedureDto() {
  }

  public ProcedureDto(Procedure procedure) {
    this.id = procedure.getId();
    this.name = procedure.getName();
  }
}
