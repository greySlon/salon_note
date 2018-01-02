package com.greyslon.abi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greyslon.abi.models.Person;

import java.util.stream.Collectors;

public class ClientDetales {

  @JsonProperty(value = "first_name")
  public String firstName;

  @JsonProperty(value = "middle_name")
  public String middleName;

  @JsonProperty(value = "last_name")
  public String lastName;

  @JsonProperty(value = "phones")
  public String phones;

  @JsonProperty(value = "photo")
  public String photo;

  public ClientDetales(Person client) {
    this.firstName = client.getFirstName();
    this.middleName = client.getMiddleName();
    this.lastName = client.getLastName();
    this.photo = client.getPhoto();
    this.phones = client.getPhones().stream()
        .map(phone -> phone.getPhoneNumber())
        .collect(Collectors.joining(","));
  }
}
