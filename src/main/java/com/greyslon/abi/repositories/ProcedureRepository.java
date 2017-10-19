package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Procedure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

  Optional<Procedure> findById(Long id);

  List<Procedure> findAll();

  Procedure save(Procedure procedure);

  @Modifying
  @Query("UPDATE Procedure p SET p.enabled = false WHERE p.id = ?1")
  void disable(Long id);
}
