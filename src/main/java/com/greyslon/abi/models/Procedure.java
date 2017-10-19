package com.greyslon.abi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

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
  public Long id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "enabled", columnDefinition = "BIT(1) NULL DEFAULT 1")
  private Boolean enabled;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "procedures")
  private Set<WorkItem> workItems;


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

  public Set<WorkItem> getWorkItems() {
    return workItems;
  }

  public void setWorkItems(Set<WorkItem> workItems) {
    this.workItems = workItems;
  }
}