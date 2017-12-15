package com.greyslon.abi.controllers;

import com.greyslon.abi.models.Master;
import com.greyslon.abi.services.MasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterController {

  @Autowired
  private MasterService masterService;

  @RequestMapping(value = "/all")
  public List<Master> getAll() {
    return masterService.getAll();
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Master add(@RequestBody Master master) {
    return masterService.add(master);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Master update(@RequestBody Master master) {
    return masterService.update(master);
  }

  @RequestMapping(value = "disable", method = RequestMethod.POST)
  public void disable(@RequestParam(name = "id") Long id) {
    masterService.disable(id);
  }
}