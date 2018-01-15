package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Person;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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

  @Query(value = "select p from Person as p"
      + " where p.name like ?1")
  List<Person> findByPartOfNames(String part1, Pageable pageable);

  @Query(value = "select p from Person as p"
      + " where p.name like ?1 and p.name like ?2")
  List<Person> findByPartOfNames(String part1, String part2, Pageable pageable);

  @Query(value = "select p from Person as p"
      + " where p.name like ?1 and p.name like ?2 and p.name like ?3")
  List<Person> findByPartOfNames(String part1, String part2, String part3, Pageable pageable);

  default List<Person> findByPartOfNames(List<String> parts, Pageable pageable) {
    if (parts == null || parts.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    if (parts.size() == 1) {
      return findByPartOfNames(parts.get(0), pageable);
    }
    if (parts.size() == 2) {
      return findByPartOfNames(parts.get(0), parts.get(1), pageable);
    }
    return findByPartOfNames(parts.get(0), parts.get(1), parts.get(2), pageable);
  }

  Person save(Person person);
}
