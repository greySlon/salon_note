package com.greyslon.abi.domain;

import com.greyslon.abi.models.Person;
import com.greyslon.abi.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<Person> {

  @Autowired
  private PersonService personService;

  @Override
  public Person getCurrentAuditor() {
    return personService.findPerson(1L);
  }
}
