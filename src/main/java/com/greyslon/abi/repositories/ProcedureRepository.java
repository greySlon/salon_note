package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Procedure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

  Optional<Procedure> findById(Long id);

  @Query("select p from Procedure p where p.enabled = true ")
  List<Procedure> findAllEnabled();


  @Query("select p from Procedure p where p.enabled = false ")
  List<Procedure> findAllDisabled();

  @Transactional
  @Modifying
  @Query("UPDATE Procedure p SET p.enabled = false WHERE p.id = ?1")
  void disable(Long id);

  @Transactional
  @Modifying
  @Query("UPDATE Procedure p SET p.enabled = true WHERE p.id = ?1")
  void enable(Long id);

  Procedure save(Procedure procedure);
}
