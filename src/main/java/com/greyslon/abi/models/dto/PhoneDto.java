package com.greyslon.abi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greyslon.abi.models.Phone;

public class PhoneDto {

  @JsonProperty(value = "phone_number")
  public String phoneNumber;

  @JsonProperty(value = "comment")
  public String comment;

  public PhoneDto() {
  }

  public PhoneDto(Phone phone) {
    this.phoneNumber = phone.getPhoneNumber();
    this.comment = phone.getComment();
  }
}
