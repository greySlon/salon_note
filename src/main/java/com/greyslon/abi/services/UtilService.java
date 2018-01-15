package com.greyslon.abi.services;

import com.greyslon.abi.models.Person;
import com.greyslon.abi.models.dto.NamePhonePair;
import com.greyslon.abi.repositories.PersonRepository;
import com.greyslon.abi.repositories.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilService {

  @Autowired
  private PhoneRepository phoneRepository;
  @Autowired
  private PersonRepository personRepository;
  private final String template = "%{0}%";

  public List<NamePhonePair> getPairByPhone(String part) {
    if (part == null || part.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    part = part.replaceAll("\\D+", "");
    return phoneRepository
        .findByPhoneNumberIsLike(MessageFormat.format(template, part), new PageRequest(0, 5));
  }

  @Transactional
  public List<NamePhonePair> getPairByName(String part) {
    if (part == null || part.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    List<Person> persons = personRepository.findByPartOfNames(Arrays.stream(part.split("\\s"))
            .filter(s -> !s.isEmpty())
            .map(s -> MessageFormat.format(template, s))
            .collect(Collectors.toList())
        , new PageRequest(0, 5));
    return persons.stream()
        .map(p -> new NamePhonePair(
                p.getName(),
                p.getPhones().stream()
                    .map(ph -> ph.getPhoneNumber())
                    .collect(Collectors.toList())
            )
        )
        .collect(Collectors.toList());
  }
}
