package com.greyslon.abi.controllers;

import com.greyslon.abi.models.ClientDto;
import com.greyslon.abi.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public void add(@RequestBody ClientDto clientDto) {
    clientService.add(clientDto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public void update(@RequestBody ClientDto clientDto,
      @RequestParam(name = "client_id") Long clientId) {
    clientService.update(clientDto, clientId);
  }

  @RequestMapping(value = "/all")
  public List<ClientDto> getAll(Pageable pageable) {
    return clientService.getClients(pageable);
  }

}
