package com.greyslon.abi.models;

import com.greyslon.abi.models.dto.PersonDto;

import java.util.Arrays;
import java.util.HashSet;
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
@Table(name = "persons")
public class Person {

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

  @Column(name = "photo")
  private String photo;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "is_master", columnDefinition = "bit(1) default 0")
  private boolean master = false;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
  private Set<Phone> phones = new HashSet<>();

//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
//  private List<WorkItem> workItems = new ArrayList<>();


  public Person() {
  }

  public Person(PersonDto personDto) {
//    if (personDto.phones != null) {
//      Arrays.stream(personDto.phones)
//          .map(phoneDto -> new Phone(phoneDto))
//          .forEach(this::addPhone);
//    }
    this.firstName = personDto.firstName;
    this.middleName = personDto.middleName;
    this.lastName = personDto.lastName;
    this.email = personDto.email;
    this.password = personDto.password;
  }

  public boolean addPhone(Phone phone) {
    phone.setPerson(this);
    return this.phones.add(phone);
  }

  public boolean removePhone(Phone phone) {
    phone.setPerson(null);
    return this.phones.remove(phone);
  }

  public void clearPhones() {
    this.phones.forEach(phone -> phone.setPerson(null));
    this.phones.clear();
  }

//  public void addWorkItem(WorkItem workItem) {
//    this.workItems.add(workItem);
//    workItem.setClient(this);
//  }
//
//  public void removeWorkItem(WorkItem workItem) {
//    this.workItems.remove(workItem);
//    workItem.setClient(null);
//  }

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
//
//  public List<WorkItem> getWorkItems() {
//    return workItems;
//  }
//
//  public void setWorkItems(List<WorkItem> workItems) {
//    this.workItems = workItems;
//  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isMaster() {
    return master;
  }

  public void setMaster(boolean master) {
    this.master = master;
  }
}
