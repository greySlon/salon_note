package com.greyslon.abi.controllers;

import static com.greyslon.abi.domain.ResponseKey.MASTER;
import static com.greyslon.abi.domain.ResponseKey.MASTER_LIST;
import static com.greyslon.abi.domain.ResponseKey.STATUS;

import com.greyslon.abi.domain.Response;
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
  private Response response;
  @Autowired
  private MasterService masterService;

  @RequestMapping(value = "/all")
  public Response getAll() {
    try {
      List<Master> masterList = masterService.getAll();
      response.put(MASTER_LIST, masterList);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Response add(@RequestBody Master master) {
    try {
      Master addedMaster = masterService.add(master);
      response.put(MASTER, addedMaster);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Response update(@RequestBody Master master) {
    try {
      Master updatedMaster = masterService.update(master);
      response.put(MASTER, updatedMaster);
    } catch (Exception e) {
      response.put(STATUS, e.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "disable", method = RequestMethod.POST)
  public Response disable(@RequestParam(name = "id") Long id) {
    try {
      masterService.disable(id);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

}