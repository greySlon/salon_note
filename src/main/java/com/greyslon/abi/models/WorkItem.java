package com.greyslon.abi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
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

  @Column(name = "canceled", columnDefinition = "BIT(1) NULL DEFAULT 0")
  private boolean canceled;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "workitem_procedure",
      joinColumns = @JoinColumn(name = "workitem_id"),
      inverseJoinColumns = @JoinColumn(name = "procedure_id")
  )
  private Set<Procedure> procedures;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "master_id")
  private Master master;


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

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
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

  public Master getMaster() {
    return master;
  }

  public void setMaster(Master master) {
    this.master = master;
  }
}
