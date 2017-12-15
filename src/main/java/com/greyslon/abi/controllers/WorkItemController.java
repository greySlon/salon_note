package com.greyslon.abi.controllers;

import com.greyslon.abi.models.WorkItem;
import com.greyslon.abi.services.WorkItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
  public WorkItem add(@RequestBody WorkItem workItem) {
    return workItemService.add(workItem);
  }

  @RequestMapping(value = "/get")
  public List<WorkItem> getWorkItemsByDate(@RequestParam(name = "master_id") Long master_id,
      @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
      Pageable pageable) {
    return workItemService.getByDate(date, master_id, pageable);
  }

  @RequestMapping(value = "/get_week")
  public Map<Integer, Map<String, Object>> getWorkItemsByWeek(
      @RequestParam(name = "master_id") Long master_id,
      @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
      @RequestParam(name = "week") long week) {
    return workItemService.getWeekSchedule(date, master_id, week);
  }

  @RequestMapping(value = "/get_all")
  public List<WorkItem> getAllWorkItemByDate(
      @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
      Pageable pageable) {
    return workItemService.getByDate(date, pageable);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public WorkItem update(@RequestBody WorkItem workItem) {
    return workItemService.update(workItem);
  }

  @RequestMapping(value = "/cancel", method = RequestMethod.POST)
  public void cancel(@RequestParam(name = "id") Long id) {
    workItemService.cancel(id);
  }
}
