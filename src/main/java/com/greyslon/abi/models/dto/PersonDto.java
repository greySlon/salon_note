package com.greyslon.abi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greyslon.abi.models.Person;

import java.util.stream.Collectors;

public class PersonDto {

  @JsonProperty(value = "first_name")
  public String firstName;

  @JsonProperty(value = "middle_name")
  public String middleName;

  @JsonProperty(value = "last_name")
  public String lastName;

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
    this.firstName = person.getFirstName();
    this.middleName = person.getMiddleName();
    this.lastName = person.getLastName();
    this.email = person.getEmail();
    this.photo = person.getPhoto();
    this.phones = person.getPhones().stream()
        .map(phone -> new PhoneDto(phone))
        .collect(Collectors.toList())
        .toArray(new PhoneDto[0]);
  }
}
