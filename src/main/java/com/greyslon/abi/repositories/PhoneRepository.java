package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

  Optional<Phone> findById(Long id);

  @Query("SELECT p FROM Phone p WHERE p.phoneNumber = ?1")
  Optional<Phone> findByPhoneNumber(String phoneNumber);

  Phone save(Phone phone);
}
