package com.greyslon.abi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.stream.Collectors;

public class ClientDto {

  @JsonProperty(value = "first_name")
  public String firstName;

  @JsonProperty(value = "middle_name")
  public String middleName;

  @JsonProperty(value = "last_name")
  public String lastName;

  @JsonProperty(value = "phones")
  public PhoneDto[] phones;

  public ClientDto() {
  }

  public ClientDto(Client client) {
    this.firstName = client.getFirstName();
    this.middleName = client.getMiddleName();
    this.lastName = client.getLastName();
    this.phones = client.getPhones().stream()
        .map(phone -> new PhoneDto(phone))
        .collect(Collectors.toList())
        .toArray(new PhoneDto[0]);
  }
}
