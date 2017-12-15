package com.greyslon.abi.controllers;

import com.greyslon.abi.models.Procedure;
import com.greyslon.abi.services.ProcedureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/procedure")
public class ProcedureController {

  @Autowired
  private ProcedureService procedureService;

  @RequestMapping(value = "/all")
  public List<Procedure> getProcedures() {
    return procedureService.getAllProcedures();
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Procedure add(@RequestBody Procedure procedure) {
    return procedureService.save(procedure);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Procedure update(@RequestBody Procedure procedure) {
    return procedureService.update(procedure);
  }

  @RequestMapping(value = "/disable", method = RequestMethod.POST)
  public void disable(@RequestParam(name = "id") Long id) {
    procedureService.disable(id);
  }
}
