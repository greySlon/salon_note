package com.greyslon.abi.controllers;

import com.greyslon.abi.models.dto.WorkItemDto;
import com.greyslon.abi.services.WorkItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workitem")
public class WorkItemController {

  @Autowired
  private WorkItemService workItemService;

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void add(@RequestBody WorkItemDto workItemDto) {
    workItemService.add(workItemDto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void update(@RequestBody WorkItemDto workItemDto) {
    workItemService.update(workItemDto);
  }

  @RequestMapping(value = "/get")
  public List<WorkItemDto> getWorkItemsByDate(@RequestParam(name = "master_id") Long master_id,
      @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
    return workItemService.getByDate(date, master_id);
  }

  @RequestMapping(value = "/get_week")
  public Map<Integer, List<WorkItemDto>> getWorkItemsByWeek(
      @RequestParam(name = "master_id") Long master_id,
      @RequestParam(name = "week_offset") long weekOffset) {
    return workItemService.getWeekSchedule(master_id, weekOffset);
  }

  @RequestMapping(value = "/get_all")
  public List<WorkItemDto> getAllWorkItemByDate(
      @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
    return workItemService.getByDate(date);
  }

  @RequestMapping(value = "/cancel", method = RequestMethod.POST)
  public void cancel(@RequestParam(name = "masterId") Long id) {
    workItemService.cancel(id);
  }
}
