package com.greyslon.abi.services;

import com.greyslon.abi.exceptions.PersonNotFoundException;
import com.greyslon.abi.exceptions.PersonNotSpecifiedException;
import com.greyslon.abi.exceptions.PhoneAnotherOwnerException;
import com.greyslon.abi.exceptions.PhoneNotFoundException;
import com.greyslon.abi.models.Person;
import com.greyslon.abi.models.Phone;
import com.greyslon.abi.models.dto.PersonDto;
import com.greyslon.abi.models.dto.PhoneDto;
import com.greyslon.abi.repositories.PersonRepository;
import com.greyslon.abi.repositories.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private PhoneRepository repository;

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public void add(PersonDto personDto) {
    Person person = new Person(personDto);
    attachPhones(personDto.phones, person);
    personRepository.save(person);
  }

  public void update(PersonDto personDto) {
    Person personStored = findPerson(personDto.id);

    String name = personDto.name;
    String email = personDto.email;
    String password = personDto.password;

    if (!isNull(name)) {
      personStored.setName(name);
    }
    if (!isNull(email)) {
      personStored.setEmail(email);
    }
    if (!isNull(password)) {
      personStored.setPassword(password);
    }
    attachPhones(personDto.phones, personStored);
    personRepository.save(personStored);
  }

  private void attachPhones(PhoneDto[] phoneDtos, Person person) {
    if (phoneDtos != null) {
      person.clearPhones();
      Arrays.stream(phoneDtos)
          .map(phoneDto -> new Phone(phoneDto))
          .map(phone -> {
            Phone phone1 = repository.findByPhoneNumber(phone.getPhoneNumber()).orElse(phone);
            checkPhoneOwner(person, phone1);
            phone1.setComment(phone.getComment());
            return phone1;
          })
          .forEach(person::addPhone);
    }
  }

  private void checkPhoneOwner(Person person, Phone phone) {
    if (!(phone.getPerson() == null || phone.getPerson().equals(person))) {
      throw new PhoneAnotherOwnerException(phone.getPhoneNumber());
    }
  }

  public List<PersonDto> getClients(Pageable pageable) {
    return personRepository.findAllClients(pageable).stream()
        .map(client -> new PersonDto(client))
        .collect(Collectors.toList());
  }

  public List<PersonDto> getMasters() {
    return personRepository.findAllMasters().stream()
        .map(p -> new PersonDto(p))
        .collect(Collectors.toList());
  }

  public void createMaster(Long id) {
    Person person = findPerson(id);
    person.setMaster(true);
    personRepository.save(person);
  }

  public void removeMaster(Long id) {
    Person person = findPerson(id);
    person.setMaster(false);
    personRepository.save(person);
  }

  public PersonDto getByPhone(String phone) {
    Person person = personRepository.findByPhones(phone)
        .orElseThrow(() -> new PhoneNotFoundException());
    return new PersonDto(person);
  }

  public Person findPerson(Long clientId) {
    if (clientId == null) {
      throw new PersonNotSpecifiedException();
    }
    return personRepository.findById(clientId).orElseThrow(() -> new PersonNotFoundException());
  }

  private boolean isNull(String str) {
    return str == null;
  }
}
