package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Master;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterRepository extends PagingAndSortingRepository<Master, Long> {

  Master save(Master master);

  List<Master> findAll();

  Optional<Master> findById(Long id);

  @Modifying
  @Query("UPDATE Master m set m.enabled = 0 where m.id = ?1")
  void disable(Long id);
}
