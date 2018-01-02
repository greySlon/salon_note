package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Person;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Transactional(propagation = Propagation.NESTED)
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  Optional<Person> findById(Long Id);

  @Query("select p from Person p where p.master = true ")
  List<Person> findAllMasters();

  @Query("select p from Person p where p.master = false ")
  List<Person> findAllClients(Pageable pageable);

  @Query(value = "select * from persons as p "
      + " join phones as ph on p.id = ph.person_id "
      + " where ph.phone_number = ?1", nativeQuery = true)
  Optional<Person> findByPhones(String phone);

  List<Person> findAllByFirstName(String firstName);

  List<Person> findAllByLastName(String lastName);

  Person save(Person person);
}
