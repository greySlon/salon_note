package com.greyslon.abi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greyslon.abi.models.Person;

import java.util.stream.Collectors;

public class PersonDto {

  @JsonProperty(value = "id")
  public Long id;

  @JsonProperty(value = "name")
  public String name;

  @JsonProperty(value = "phones")
  public PhoneDto[] phones;

  @JsonProperty(value = "photo")
  public String photo;

  @JsonProperty(value = "email")
  public String email;

  @JsonProperty(value = "password")
  public String password;

  public PersonDto() {
  }

  public PersonDto(Person person) {
    this.id = person.getId();
    this.name = person.getName();
    this.email = person.getEmail();
    this.photo = person.getPhoto();
    this.phones = person.getPhones().stream()
        .map(phone -> new PhoneDto(phone))
        .collect(Collectors.toList())
        .toArray(new PhoneDto[0]);
  }
}
