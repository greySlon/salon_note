package com.greyslon.abi.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

  @Column(name = "middle_name")
  private String middleName;

  @Column(name = "last_name")
  private String lastName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
  private Set<Phone> phones = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
  private List<WorkItem> workItems = new ArrayList<>();

  public Client() {
  }

  public Client(ClientDto clientDto) {
    if (clientDto.phones != null) {
      Arrays.stream(clientDto.phones)
          .map(phoneDto -> new Phone(phoneDto))
          .forEach(this::addPhone);
    }
    this.firstName = clientDto.firstName;
    this.middleName = clientDto.middleName;
    this.lastName = clientDto.lastName;
  }

  public boolean addPhone(Phone phone) {
    phone.setClient(this);
    return this.phones.add(phone);
  }

  public boolean removePhone(Phone phone) {
    phone.setClient(null);
    return this.phones.remove(phone);
  }

  public void addWorkItem(WorkItem workItem) {
    this.workItems.add(workItem);
    workItem.setClient(this);
  }

  public void removeWorkItem(WorkItem workItem) {
    this.workItems.remove(workItem);
    workItem.setClient(null);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<Phone> getPhones() {
    return phones;
  }

  public void setPhones(Set<Phone> phones) {
    this.phones = phones;
  }

  public List<WorkItem> getWorkItems() {
    return workItems;
  }

  public void setWorkItems(List<WorkItem> workItems) {
    this.workItems = workItems;
  }
}
