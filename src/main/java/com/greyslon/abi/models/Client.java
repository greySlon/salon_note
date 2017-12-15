package com.greyslon.abi.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "midle_name")
  private String midleName;

  @Column(name = "last_name")
  private String lastName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
  private Set<Phone> phones;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
  private Set<WorkItem> workItems;
}
