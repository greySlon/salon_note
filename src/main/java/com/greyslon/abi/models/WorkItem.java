package com.greyslon.abi.models;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workitems")
public class WorkItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Client client;

  @Column(name = "service_time")
  private LocalDateTime serviceTime;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "workitem_procedure",
      joinColumns = @JoinColumn(name = "workitem_id"),
      inverseJoinColumns = @JoinColumn(name = "procedure_id")
  )
  private Set<Procedure> procedures;
}
