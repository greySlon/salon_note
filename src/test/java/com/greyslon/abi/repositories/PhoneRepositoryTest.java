package com.greyslon.abi.repositories;

import com.greyslon.abi.models.Person;
import com.greyslon.abi.services.UtilService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PhoneRepositoryTest {

  @Autowired
  private PhoneRepository phoneRepository;
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private UtilService utilService;

//  @Test
//  public void findByPhoneNumberIsLike() throws Exception {
////    List<NamePhonePair> byPhoneNumberIsLike = phoneRepository
////        .findByPhoneNumberIsLike("%2%", new PageRequest(0, 5));
//  }

  @Test
  public void findByNameLike() throws Exception {
//    List<Person> byPartOfNames = personRepository.findByPartOfNames("%g%"
//        , "%r%"
//        , "%%");

//    List<NamePhonePair> byPhoneNumberIsLike = utilService.getPairByName("ee");

//        phoneRepository
//        .findByPhoneNumberIsLike("%ee%", new PageRequest(0, 5));

//    List<NamePhonePair> byFirstNameLike = personRepository
//        .findByFirstNameLike("%se%", new PageRequest(0, 5));
  }
}