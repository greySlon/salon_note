package com.greyslon.abi.services;

import com.greyslon.abi.exceptions.ProcedureNotFoundException;
import com.greyslon.abi.exceptions.ProcedureNotSpecifiedException;
import com.greyslon.abi.models.Procedure;
import com.greyslon.abi.models.ProcedureDto;
import com.greyslon.abi.repositories.ProcedureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureService {

  @Autowired
  private ProcedureRepository procedureRepository;

  public List<ProcedureDto> getActual() {
    return procedureRepository.findAllEnabled().stream()
        .map(p -> new ProcedureDto(p))
        .collect(Collectors.toList());
  }

  public List<ProcedureDto> getArchived() {
    return procedureRepository.findAllDisabled().stream()
        .map(p -> new ProcedureDto(p))
        .collect(Collectors.toList());
  }

  public void save(ProcedureDto procedureDto) {
    procedureRepository.save(new Procedure(procedureDto));
  }

  public void update(ProcedureDto procedureDto) {
    Procedure procedure = findProcedure(procedureDto.id);
    procedure.setName(procedureDto.name);
    procedureRepository.save(procedure);
  }

  public void disable(Long id) {
    procedureRepository.disable(id);
  }

  public void enable(Long id) {
    procedureRepository.enable(id);
  }

  public Procedure findProcedure(Long procedureId) {
    if (procedureId == null) {
      throw new ProcedureNotSpecifiedException();
    }
    return procedureRepository.findById(procedureId)
        .orElseThrow(() -> new ProcedureNotFoundException());
  }
}
