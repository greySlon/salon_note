package com.greyslon.abi.services;

import com.greyslon.abi.exceptions.WorkItemNotFoundException;
import com.greyslon.abi.exceptions.WorkItemNotSpecifiedException;
import com.greyslon.abi.models.Person;
import com.greyslon.abi.models.WorkItem;
import com.greyslon.abi.models.dto.ProcedureDto;
import com.greyslon.abi.models.dto.WeekSchedule;
import com.greyslon.abi.models.dto.WorkItemDto;
import com.greyslon.abi.repositories.WorkItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WorkItemService {

  @Autowired
  private WorkItemRepository workItemRepository;
  @Autowired
  private ProcedureService procedureService;
  @Autowired
  private PersonService personService;

  public void add(WorkItemDto workItemDto) {
    WorkItem workItem = workItemDto.buildWorkItem();
    for (ProcedureDto procedure : workItemDto.procedures) {
      workItem.addProcedure(procedureService.findProcedure(procedure.id));
    }
    Person master = personService.findPerson(workItemDto.masterId);
    Person client = personService.findPerson(workItemDto.clientId);
    workItem.setMaster(master);
    workItem.setClient(client);

    workItemRepository.save(workItem);
  }

  public void update(WorkItemDto workItemDto) {
    if (workItemDto.id == null) {
      throw new WorkItemNotSpecifiedException();
    }
    WorkItem workItem = workItemRepository.findById(workItemDto.id)
        .orElseThrow(() -> new WorkItemNotFoundException());

    if (workItemDto.comment != null) {
      workItem.setComment(workItemDto.comment);
    }
    if (workItemDto.serviceDate != null) {
      workItem.setServiceDate(workItemDto.serviceDate);
    }
    if (workItemDto.serviceTime != null) {
      workItem.setServiceTime(workItemDto.serviceTime);
    }
    if (workItemDto.masterId != null && workItemDto.masterId != workItem.getMaster().getId()) {
      Person master = personService.findPerson(workItemDto.masterId);
      workItem.setMaster(master);
    }
    if (workItemDto.clientId != null && workItemDto.clientId != workItem.getClient().getId()) {
      Person client = personService.findPerson(workItemDto.clientId);
    }
    if (workItemDto.procedures != null) {
      workItem.getProcedures().clear();
      for (ProcedureDto procedure : workItemDto.procedures) {
        workItem.addProcedure(procedureService.findProcedure(procedure.id));
      }
    }
    workItemRepository.save(workItem);
  }

  public List<WorkItemDto> getByDate(LocalDate date, Long masterId) {
    Person master = personService.findPerson(masterId);
    return workItemRepository.findByServiceDateAndMaster(date, master).stream()
        .map(workItem -> new WorkItemDto(workItem))
        .collect(Collectors.toList());
  }

  public Map<Integer, WeekSchedule> getWeekSchedule(Long masterId, long weekOffset) {
    LocalDate currentDate = LocalDate.now();
    LocalDate startDate = currentDate
        .minusDays(currentDate.getDayOfWeek().ordinal() + 7 * weekOffset);
    LocalDate endDate = startDate.plusDays(6);
    List<WorkItem> weekSchedule = workItemRepository
        .findByPeriodAndMaster(startDate, endDate, masterId);

    Map<Integer, WeekSchedule> map = new HashMap<>();
    IntStream.range(0, 7).forEach(day -> {
      List<WorkItemDto> dtoList = weekSchedule.stream()
          .filter(wi -> wi.getServiceDate().getDayOfWeek().ordinal() == day)
          .map(wi -> new WorkItemDto(wi))
          .collect(Collectors.toList());
      WeekSchedule schedule = new WeekSchedule();
      schedule.serviceDate = startDate.plusDays(day);
      schedule.workItemDtos = dtoList;
      map.put(day, schedule);
    });
    return map;
  }

  public List<WorkItemDto> getByDate(LocalDate date) {
    return workItemRepository.findByServiceDate(date).stream()
        .map(workItem -> new WorkItemDto(workItem))
        .collect(Collectors.toList());
  }

  public void cancel(Long id) {
    workItemRepository.cancel(id);
  }
}