package com.greyslon.abi.controllers;

import com.greyslon.abi.models.ProcedureDto;
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

  @RequestMapping(value = "/actual")
  public List<ProcedureDto> getActual() {
    return procedureService.getActual();
  }

  @RequestMapping(value = "/archived")
  public List<ProcedureDto> getArchived() {
    return procedureService.getArchived();
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void add(@RequestBody ProcedureDto procedureDto) {
    procedureService.save(procedureDto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void update(@RequestBody ProcedureDto procedureDto) {
    procedureService.update(procedureDto);
  }

  @RequestMapping(value = "/disable", method = RequestMethod.POST)
  public void disable(@RequestParam(name = "id") Long id) {
    procedureService.disable(id);
  }

  @RequestMapping(value = "/enable", method = RequestMethod.POST)
  public void enable(@RequestParam(name = "id") Long id) {
    procedureService.enable(id);
  }
}
