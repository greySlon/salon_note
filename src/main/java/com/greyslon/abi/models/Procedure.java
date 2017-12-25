package com.greyslon.abi.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "procedures")
public class Procedure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "enabled", columnDefinition = "BIT(1) DEFAULT 1", nullable = false)
  private Boolean enabled = true;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "procedures")
  private List<WorkItem> workItems;


  public Procedure() {

  }

  public Procedure(ProcedureDto procedureDto) {
    this.name = procedureDto.name;
  }

  public Procedure(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public List<WorkItem> getWorkItems() {
    return workItems;
  }

  public void setWorkItems(List<WorkItem> workItems) {
    this.workItems = workItems;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Procedure procedure = (Procedure) o;
    return Objects.equals(name, procedure.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}