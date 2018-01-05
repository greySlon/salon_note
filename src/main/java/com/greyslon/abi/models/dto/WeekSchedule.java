package com.greyslon.abi.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class WeekSchedule {

  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonProperty(value = "service_date")
  public LocalDate serviceDate;
  @JsonProperty(value = "workitems")
  public List<WorkItemDto> workItemDtos;
}
