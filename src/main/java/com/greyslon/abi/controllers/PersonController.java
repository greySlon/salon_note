package com.greyslon.abi.controllers;

import com.greyslon.abi.models.dto.PersonDto;
import com.greyslon.abi.repositories.PersonRepository;
import com.greyslon.abi.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;
  @Autowired
  private PersonRepository personRepository;

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void add(@RequestBody PersonDto personDto) {
    personService.add(personDto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void update(@RequestBody PersonDto personDto) {
    personService.update(personDto);
  }

  @RequestMapping(value = "/clients")
  public List<PersonDto> getClients(Pageable pageable) {
    return personService.getClients(pageable);
  }

  @RequestMapping(value = "/masters")
  public List<PersonDto> getMasters() {
    return personService.getMasters();
  }

  @RequestMapping(value = "/master/create", method = RequestMethod.POST)
  public void createMaster(@RequestParam(name = "person_id") Long id) {
    personService.createMaster(id);
  }

  @RequestMapping(value = "/master/remove", method = RequestMethod.POST)
  public void removeMaster(@RequestParam(name = "person_id") Long id) {
    personService.removeMaster(id);
  }

  @RequestMapping(value = "/get/by_phone", method = RequestMethod.POST)
  public PersonDto getByPhone(@RequestParam(name = "phone") String phone) {
    return personService.getByPhone(phone);
  }
}
