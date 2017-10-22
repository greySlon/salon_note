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
import java.util.List;

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
    WorkItem workItemMerged = Utils.updateState(workItemStored, workItem);
    return workItemMerged;
  }
}