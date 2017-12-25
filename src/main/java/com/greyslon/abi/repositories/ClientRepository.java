package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Client;
import com.greyslon.abi.models.Phone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  Client save(Client client);

  Page<Client> findAll(Pageable pageable);

  Optional<Client> findById(Long clientId);

  @Query("select c from Client c join Phone p where p = ?1")
  Optional<Client> findByPhones(Phone phone);
}
