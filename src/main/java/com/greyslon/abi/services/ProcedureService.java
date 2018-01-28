package com.greyslon.abi.services;

import com.greyslon.abi.exceptions.ApplicationException;
import com.greyslon.abi.models.Procedure;
import com.greyslon.abi.models.dto.ProcedureDto;
import com.greyslon.abi.repositories.ProcedureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureService {

  @Autowired
  private MessageSource messageSource;
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

  @Transactional
  public Procedure findProcedure(Long procedureId) {
    if (procedureId == null) {
      throw new ApplicationException("procedure.not_specified");
    }
    return procedureRepository.findById(procedureId)
        .orElseThrow(() -> new ApplicationException("procedure.not_found"));
  }
}
