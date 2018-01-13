package com.greyslon.abi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.stream.Collectors;

public class NamePhonePair {

  @JsonProperty(value = "name")
  public String name;
  @JsonProperty(value = "phone")
  public String phone;

  public NamePhonePair() {
  }

  public NamePhonePair(String name, String phone) {
    this.name = name;
    this.phone = phone;
  }

  public NamePhonePair(String name, Collection<String> phone) {
    this.name = name;
    this.phone = phone.stream().collect(Collectors.joining(","));
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }
}
