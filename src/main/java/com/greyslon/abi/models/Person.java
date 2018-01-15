package com.greyslon.abi.models;

import com.greyslon.abi.models.dto.PersonDto;

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

  @Column(name = "name")
  private String name;

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


  public Person() {
  }

  public Person(PersonDto personDto) {
//    if (personDto.phones != null) {
//      Arrays.stream(personDto.phones)
//          .map(phoneDto -> new Phone(phoneDto))
//          .forEach(this::addPhone);
//    }
    this.name = personDto.name;
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

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Phone> getPhones() {
    return phones;
  }

  public void setPhones(Set<Phone> phones) {
    this.phones = phones;
  }

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
