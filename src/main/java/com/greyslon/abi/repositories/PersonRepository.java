package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Person;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

  @Query(value =
      "select p from Person as p where p.firstName like ?1")
  List<Person> findByFirstNameLike(String nameWrapped, Pageable pageable);

  List<Person> findAllByFirstName(String firstName);

  List<Person> findAllByLastName(String lastName);

  Person save(Person person);
}
