package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Person;
import com.greyslon.abi.models.WorkItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {

  Optional<WorkItem> findById(Long id);

  Page<WorkItem> findAll(Pageable pageable);

  @Query("SELECT wi FROM WorkItem wi where wi.serviceDate = ?1 and wi.master = ?2")
  List<WorkItem> findByServiceDateAndMaster(LocalDate date, Person master);

  @Query("SELECT wi FROM WorkItem wi where wi.serviceDate = ?1")
  List<WorkItem> findByServiceDate(LocalDate date);

  @Query("SELECT wi FROM WorkItem wi "
      + "where wi.canceled = false "
      + "and wi.serviceDate between ?1 and ?2 "
      + "and wi.master.id = ?3 ")
  List<WorkItem> findByPeriodAndMaster(LocalDate startDate, LocalDate endDate, Long masterId);

  @Transactional
  @Modifying
  @Query("UPDATE WorkItem wi SET wi.canceled = true WHERE wi.id = ?1")
  void cancel(Long id);

  @Transactional
  @Modifying
  @Query("UPDATE WorkItem wi SET wi.canceled = false WHERE wi.id = ?1")
  void undoCancel(Long id);

  WorkItem save(WorkItem workItem);
}