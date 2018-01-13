package com.greyslon.abi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greyslon.abi.models.Person;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ClientDetails {

  @JsonProperty(value = "name")
  public String name;

  @JsonProperty(value = "phones")
  public String phones;

  @JsonProperty(value = "photo")
  public String photo;

  public ClientDetails() {
  }

  public ClientDetails(Person client) {
    this.name = Arrays.asList(client.getFirstName(), client.getMiddleName(), client.getLastName())
        .stream()
        .collect(Collectors.joining(" "));
    this.photo = client.getPhoto();
    this.phones = client.getPhones().stream()
        .map(phone -> phone.getPhoneNumber())
        .collect(Collectors.joining(","));
  }
}
