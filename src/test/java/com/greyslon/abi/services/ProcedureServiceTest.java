package com.greyslon.abi.services;

import com.greyslon.abi.repositories.ProcedureRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
public class ProcedureServiceTest {

  @InjectMocks
  private ProcedureService procedureService;
  @Mock
  private ProcedureRepository procedureRepository;

  @Test
  public void shouldUpdate() throws Exception {

  }

}