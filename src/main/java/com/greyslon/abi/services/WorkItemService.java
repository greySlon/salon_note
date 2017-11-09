package com.greyslon.abi.services;

import com.greyslon.abi.domain.Utils;
import com.greyslon.abi.exceptions.MasterNotFoundException;
import com.greyslon.abi.exceptions.ProcedureNotFoundException;
import com.greyslon.abi.exceptions.WorkItemNotFoundException;
import com.greyslon.abi.exceptions.WorkItemNotSpecifiedException;
import com.greyslon.abi.models.Master;
import com.greyslon.abi.models.WorkItem;
import com.greyslon.abi.repositories.WorkItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkItemService {

  @Autowired
  private WorkItemRepository workItemRepository;
  @Autowired
  private ProcedureService procedureService;
  @Autowired
  private MasterService masterService;

  public WorkItem add(WorkItem workItem) {
    return workItemRepository.save(workItem);
  }

  public List<WorkItem> getByDate(LocalDate date, Long masterId, Pageable pageable) {
    Master master = masterService.findByID(masterId);
    return workItemRepository.findByServiceDateGreaterThanEqualAndMaster(date, master, pageable);
  }

  public Map<Integer, Map<String, Object>> getWeekSchedule(LocalDate date, Long masterId,
      long weekNumber) {
    int offset = date.getDayOfWeek().ordinal();
    LocalDate startDate = date.minusDays(offset + 7 * weekNumber);
    LocalDate endDate = startDate.plusDays(6);

    List<WorkItem> weekSchedule = workItemRepository.getWeekSchedule(startDate, endDate, masterId);

    Map<Integer, Map<String, Object>> map = initResponse(startDate, endDate);
    return fillResponse(map, weekSchedule);
  }

  private Map<Integer, Map<String, Object>> fillResponse(
      Map<Integer, Map<String, Object>> map,
      List<WorkItem> weekSchedule) {
    for (WorkItem workItem : weekSchedule) {
      int day = workItem.getServiceDate().getDayOfWeek().ordinal();
      ((ArrayList<WorkItem>) map.get(day).get("workItems")).add(workItem);
    }
    return map;
  }

  private Map<Integer, Map<String, Object>> initResponse(LocalDate startDate,
      LocalDate endDate) {
    Map<Integer, Map<String, Object>> map = new HashMap<>();
    LocalDate emptyDate = startDate;
    while (emptyDate.getDayOfMonth() <= endDate.getDayOfMonth()) {
      Map<String, Object> innerMap = new HashMap<>();
      innerMap.put("date", emptyDate.format(DateTimeFormatter.ofPattern("dd-MM-yyy")));
      innerMap.put("workItems", new ArrayList<>());

      map.put(emptyDate.getDayOfWeek().ordinal(), innerMap);
      emptyDate = emptyDate.plusDays(1L);
    }
    return map;
  }

  public List<WorkItem> getByDate(LocalDate date, Pageable pageable) {
    return workItemRepository.findByServiceDateGreaterThanEqual(date, pageable);
  }

  public WorkItem update(WorkItem workItem)
      throws ProcedureNotFoundException, MasterNotFoundException {
    workItem = merge(workItem);
    return workItemRepository.save(workItem);
  }

  public void cancel(Long id) {
    workItemRepository.cancel(id);
  }

  private WorkItem merge(WorkItem workItem) {
    if (workItem == null || workItem.getId() == null) {
      throw new WorkItemNotSpecifiedException();
    }
    WorkItem workItemStored = workItemRepository.findById(workItem.getId())
        .orElseThrow(() -> new WorkItemNotFoundException());
    return Utils.updateState(workItemStored, workItem);
  }
}