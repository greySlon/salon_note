package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Master;
import com.greyslon.abi.models.WorkItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkItemRepository extends PagingAndSortingRepository<WorkItem, Long> {

  WorkItem save(WorkItem workItem);

  Optional<WorkItem> findById(Long id);

  Page<WorkItem> findAll(Pageable pageable);

  @Query("SELECT wi FROM WorkItem wi where wi.serviceDate >= ?1 and wi.master = ?2 and wi.canceled = false")
  List<WorkItem> findByServiceDateGreaterThanEqualAndMaster(LocalDate date, Master master,
      Pageable pageable);

  @Query("SELECT wi FROM WorkItem wi where wi.serviceDate >= ?1 and wi.canceled = false")
  List<WorkItem> findByServiceDateGreaterThanEqual(LocalDate date, Pageable pageable);

  @Query("SELECT wi FROM WorkItem wi where wi.serviceDate >= ?1 and wi.serviceDate <= ?2 and wi.canceled = false")
  List<WorkItem> getWeekSchedule(LocalDate startDate,
      LocalDate endDate);

  @Transactional
  @Modifying
  @Query("UPDATE WorkItem wi SET wi.canceled = true WHERE wi.id = ?1")
  void cancel(Long id);
}