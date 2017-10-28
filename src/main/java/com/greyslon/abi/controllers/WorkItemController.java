package com.greyslon.abi.controllers;

import static com.greyslon.abi.domain.ResponseKey.STATUS;
import static com.greyslon.abi.domain.ResponseKey.WORK_ITEM;
import static com.greyslon.abi.domain.ResponseKey.WORK_ITEM_LIST;

import com.greyslon.abi.domain.Response;
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
  private Response response;
  @Autowired
  private WorkItemService workItemService;

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Response add(@RequestBody WorkItem workItem) {
    try {
      WorkItem added = workItemService.add(workItem);
      response.put(WORK_ITEM, added);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/get")
  public Response getWorkItemsByDate(@RequestParam(name = "master_id") Long master_id,
      @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
      Pageable pageable) {
    try {
      List<WorkItem> workItems = workItemService.getByDate(date, master_id, pageable);
      response.put(WORK_ITEM_LIST, workItems);
    } catch (Exception e) {
      response.put(STATUS, e.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/get_week")
  public Response getWorkItemsByWeek(@RequestParam(name = "master_id") Long master_id,
      @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
      @RequestParam(name = "week") long week) {
    try {
      Map<Integer, WorkItem> workItems = workItemService.getWeekSchedule(date, master_id, week);
      response.put(WORK_ITEM_LIST, workItems);
    } catch (Exception e) {
      response.put(STATUS, e.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/get_all")
  public Response getAllWorkItemByDate(
      @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
      Pageable pageable) {
    try {
      List<WorkItem> workItems = workItemService.getByDate(date, pageable);
      response.put(WORK_ITEM_LIST, workItems);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Response update(@RequestBody WorkItem workItem) {
    try {
      WorkItem updated = workItemService.update(workItem);
      response.put(WORK_ITEM, updated);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/cancel", method = RequestMethod.POST)
  public Response cancel(@RequestParam(name = "id") Long id) {
    try {
      workItemService.cancel(id);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }
}
