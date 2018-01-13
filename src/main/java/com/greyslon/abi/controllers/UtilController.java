package com.greyslon.abi.controllers;

import com.greyslon.abi.models.dto.NamePhonePair;
import com.greyslon.abi.services.UtilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/utils")
public class UtilController {

  @Autowired
  private UtilService utilService;

  @RequestMapping(value = "/contact_by_phone", method = RequestMethod.POST)
  public List<NamePhonePair> getPairByPhone(@RequestParam(name = "part") String part) {
    return utilService.getPairByPhone(part);
  }

  @RequestMapping(value = "/contact_by_name", method = RequestMethod.POST)
  public List<NamePhonePair> getPairByName(@RequestParam(name = "part") String part) {
    return utilService.getPairByName(part);
  }
}
