package com.greyslon.abi.services;

import com.greyslon.abi.exceptions.ClientNotFoundException;
import com.greyslon.abi.exceptions.ClientNotSpecifiedException;
import com.greyslon.abi.models.Client;
import com.greyslon.abi.models.ClientDto;
import com.greyslon.abi.models.Phone;
import com.greyslon.abi.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  public void add(ClientDto clientDto) {
    Client client = new Client(clientDto);
    clientRepository.save(client);
  }

  public void update(ClientDto clientDto, Long clientId) {
    Client clientStored = findClient(clientId);
    if (clientDto.firstName != null) {
      clientStored.setFirstName(clientDto.firstName);
    }
    if (clientDto.middleName != null) {
      clientStored.setMiddleName(clientDto.middleName);
    }
    if (clientDto.lastName != null && !clientDto.lastName.isEmpty()) {
      clientStored.setLastName(clientDto.lastName);
    }
    if (clientDto.phones != null) {
      Arrays.stream(clientDto.phones)
          .map(phoneDto -> new Phone(phoneDto))
          .forEach(p -> {
            if (!clientStored.addPhone(p)) {
              clientStored.getPhones().stream()
                  .filter(pst -> pst.equals(p))
                  .forEach(phone -> {
                    if (p.getComment() != null) {
                      phone.setComment(p.getComment());
                    }
                  });
            }
          });
    }
    clientRepository.save(clientStored);
  }

  public List<ClientDto> getClients(Pageable pageable) {
    return clientRepository.findAll().stream()
        .map(client -> new ClientDto(client))
        .collect(Collectors.toList());
  }

  public Client findClient(Long clientId) {
    if (clientId == null) {
      throw new ClientNotSpecifiedException();
    }
    return clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException());
  }

}
