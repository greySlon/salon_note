package com.greyslon.abi.services;

import com.greyslon.abi.domain.Utils;
import com.greyslon.abi.exceptions.MasterNotFoundException;
import com.greyslon.abi.models.Master;
import com.greyslon.abi.repositories.MasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MasterService {

  @Autowired
  private MasterRepository masterRepository;

  public Master add(Master master) {
    return masterRepository.save(master);
  }

  public Master getMaster(Long id) throws MasterNotFoundException {
    return masterRepository.findById(id).orElseThrow(() -> new MasterNotFoundException());
  }

  public List<Master> getAll() {
    return masterRepository.findAll();
  }

  public Master update(Master master) throws MasterNotFoundException {
    Master merged = merge(master);
    return masterRepository.save(merged);
  }

  public Master findByID(Long id) throws MasterNotFoundException {
    return masterRepository.findById(id).orElseThrow(() -> new MasterNotFoundException());
  }


  @Transactional
  public void disable(Long id) {
    masterRepository.disable(id);
  }

  public Master merge(Master master) throws MasterNotFoundException {
    Long id = master.getId();
    Master masterStored = masterRepository.findById(id)
        .orElseThrow(() -> new MasterNotFoundException());
    return Utils.updateState(masterStored, master);
  }
}
