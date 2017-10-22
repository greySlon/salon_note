package com.greyslon.abi.services;

import com.greyslon.abi.domain.Utils;
import com.greyslon.abi.exceptions.NoProceduresSpecifiedException;
import com.greyslon.abi.exceptions.ProcedureNotFoundException;
import com.greyslon.abi.exceptions.ProcedureNotSpecifiedException;
import com.greyslon.abi.models.Procedure;
import com.greyslon.abi.repositories.ProcedureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProcedureService {

  @Autowired
  private ProcedureRepository procedureRepository;

  public List<Procedure> getAllProcedures() {
    return procedureRepository.findAll();
  }

  public Procedure save(Procedure procedure) {
    return procedureRepository.save(procedure);
  }

  public Procedure update(Procedure procedure) {
    Procedure merged = merge(procedure);
    return procedureRepository.save(merged);
  }

  public void disable(Long id) {
    procedureRepository.disable(id);
  }

  public Procedure merge(Procedure procedure) {
    if (procedure == null || procedure.getId() == null) {
      throw new ProcedureNotSpecifiedException();
    }

    Long id = procedure.getId();
    Procedure procedureStored = procedureRepository.findById(id)
        .orElseThrow(() -> new ProcedureNotFoundException());
    return Utils.updateState(procedureStored, procedure);
  }
}
