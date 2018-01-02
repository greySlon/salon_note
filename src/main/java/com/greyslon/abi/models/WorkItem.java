package com.greyslon.abi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workitems")
public class WorkItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "comment", length = 300)
  private String comment;

  @JsonFormat(shape = Shape.STRING, pattern = "HH:mm")
  @JsonProperty(value = "service_time")
  @Column(name = "service_time")
  private LocalTime serviceTime;

  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonProperty(value = "service_date")
  @Column(name = "service_date")
  private LocalDate serviceDate;

  @JsonIgnore
  @Column(name = "canceled", columnDefinition = "BIT(1) DEFAULT 0")
  private boolean canceled = false;

  @JsonIgnore
  @ManyToMany
  private Set<Procedure> procedures = new HashSet<>();

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Person client;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "master_id")
  private Person master;


  public WorkItem() {
  }

  public WorkItem(LocalDate serviceDate) {
    this.serviceDate = serviceDate;
  }

  public void addProcedure(Procedure procedure) {
    this.procedures.add(procedure);
    procedure.getWorkItems().add(this);
  }

  public void removeProcedure(Procedure procedure) {
    this.procedures.remove(procedure);
    procedure.getWorkItems().remove(this);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isCanceled() {
    return canceled;
  }

  public void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  public Person getClient() {
    return client;
  }

  public void setClient(Person client) {
    this.client = client;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public LocalTime getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(LocalTime serviceTime) {
    this.serviceTime = serviceTime;
  }

  public LocalDate getServiceDate() {
    return serviceDate;
  }

  public void setServiceDate(LocalDate serviceDate) {
    this.serviceDate = serviceDate;
  }

  public Set<Procedure> getProcedures() {
    return procedures;
  }

  public void setProcedures(Set<Procedure> procedures) {
    this.procedures = procedures;
  }

  public Person getMaster() {
    return master;
  }

  public void setMaster(Person master) {
    this.master = master;
  }
}
