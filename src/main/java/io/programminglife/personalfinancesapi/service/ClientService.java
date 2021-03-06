package io.programminglife.personalfinancesapi.service;

import java.util.List;

import io.programminglife.personalfinancesapi.entity.Client;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;

public interface ClientService {

    List<Client> findAll();

    Client findClientById(Long clientId) throws MyFinancesException;

    Client findClientByEmail(String email) throws MyFinancesException;

    Client saveClient(Client client);

    void deleteClient(Long clientId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
