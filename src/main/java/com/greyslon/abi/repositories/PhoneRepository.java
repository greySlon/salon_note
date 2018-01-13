package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Phone;
import com.greyslon.abi.models.dto.NamePhonePair;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

  Optional<Phone> findById(Long id);

  @Query("SELECT p FROM Phone p WHERE p.phoneNumber = ?1")
  Optional<Phone> findByPhoneNumber(String phoneNumber);

  Phone save(Phone phone);

  @Query(value =
      "select new com.greyslon.abi.models.dto.NamePhonePair(p.person.firstName, p.phoneNumber)"
          + " from Phone p "
          + " where p.phoneNumber like ?1 ")
  List<NamePhonePair> findByPhoneNumberIsLike(String phoneNumberWrapped, Pageable pageable);
}
