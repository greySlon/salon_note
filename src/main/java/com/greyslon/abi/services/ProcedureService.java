package com.greyslon.abi.services;

import com.greyslon.abi.domain.Utils;
import com.greyslon.abi.exceptions.ProcedureNotFoundException;
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

  public Procedure getById(Long id) throws ProcedureNotFoundException {
    return procedureRepository.findById(id).orElseThrow(() -> new ProcedureNotFoundException());
  }

  public List<Procedure> getAllProcedures() {
    return procedureRepository.findAll();
  }

  public Procedure save(Procedure procedure) {
    return procedureRepository.save(procedure);
  }

  public Procedure update(Procedure procedure) throws ProcedureNotFoundException {
    Procedure merged = merge(procedure);
    return procedureRepository.save(merged);
  }

  @Transactional
  public void disable(Long id) {
    procedureRepository.disable(id);
  }

  public Procedure merge(Procedure procedure) throws ProcedureNotFoundException {
    Procedure procedureStored = procedureRepository.findById(procedure.getId())
        .orElseThrow(() -> new ProcedureNotFoundException());
    return Utils.updateState(procedureStored, procedure);
  }

  public Set<Procedure> attach(Collection<Procedure> procedures) throws ProcedureNotFoundException {
    Set<Procedure> procedureSet = new HashSet<>();
    for (Procedure procedure : procedures) {
      procedureSet.add(getById(procedure.getId()));
    }
    return procedureSet;
  }
}
