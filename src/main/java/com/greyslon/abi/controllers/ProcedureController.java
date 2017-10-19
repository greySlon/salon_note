package com.greyslon.abi.controllers;

import static com.greyslon.abi.domain.ResponseKey.PROCEDURE;
import static com.greyslon.abi.domain.ResponseKey.PROCEDURE_LIST;
import static com.greyslon.abi.domain.ResponseKey.STATUS;

import com.greyslon.abi.domain.Response;
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
  private Response response;
  @Autowired
  private ProcedureService procedureService;

  @RequestMapping(value = "/all")
  public Response getProcedures() {
    try {
      List<Procedure> allProcedures = null;
      allProcedures = procedureService.getAllProcedures();
      response.put(PROCEDURE_LIST, allProcedures);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Response add(@RequestBody Procedure procedure) {
    try {
      Procedure savedProcedure = procedureService.save(procedure);
      response.put(PROCEDURE, savedProcedure);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Response update(@RequestBody Procedure procedure) {
    try {
      Procedure updatedProcedure = procedureService.update(procedure);
      response.put(PROCEDURE, updatedProcedure);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/disable", method = RequestMethod.POST)
  public Response disable(@RequestParam(name = "id") Long id) {
    try {
      procedureService.disable(id);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }
}
