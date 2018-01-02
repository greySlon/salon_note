package com.greyslon.abi.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greyslon.abi.models.WorkItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class WorkItemDto {

  @JsonProperty(value = "id")
  public Long id;
  @JsonProperty(value = "comment")
  public String comment;
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonProperty(value = "service_date")
  public LocalDate serviceDate;
  @JsonFormat(shape = Shape.STRING, pattern = "HH:mm")
  @JsonProperty(value = "service_time")
  public LocalTime serviceTime;

  @JsonProperty(value = "master_id")
  public Long masterId;
  @JsonProperty(value = "client_id")
  public Long clientId;
  @JsonProperty(value = "procedures")
  public ProcedureDto[] procedures;
  @JsonProperty(value = "client_detales")
  public ClientDetales clientDetails;

  public WorkItemDto() {
  }

  public WorkItemDto(WorkItem workItem) {
    this.id = workItem.getId();
    this.masterId = workItem.getMaster().getId();
    this.clientId = workItem.getClient().getId();
    this.comment = workItem.getComment();
    this.procedures = workItem.getProcedures().stream()
        .map(item -> new ProcedureDto(item))
        .collect(Collectors.toList()).toArray(new ProcedureDto[0]);
    this.serviceDate = workItem.getServiceDate();
    this.serviceTime = workItem.getServiceTime();
    this.clientDetails = new ClientDetales(workItem.getClient());
  }

  public WorkItem buildWorkItem() {
    WorkItem workItem = new WorkItem();
    workItem.setComment(this.comment);
    workItem.setServiceDate(this.serviceDate);
    workItem.setServiceTime(this.serviceTime);
    return workItem;
  }
}