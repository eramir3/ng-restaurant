package com.restaurant.service;

import com.restaurant.dto.request.ClientRequest;
import com.restaurant.dto.response.ClientResponse;
import com.restaurant.projection.client.ProfitableClients;
import com.restaurant.entity.Client;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.mapper.ClientMapper;
import com.restaurant.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public ClientResponse save(ClientRequest clientRequest) {
        Client client = clientMapper.requestToEntity(clientRequest);
        clientRepository.save(client);
        return clientMapper.entityToResponse(client);
    }

    public ClientResponse findOne(Long id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            ClientResponse clientResponse  = clientMapper.entityToResponse(client.get());
            return clientResponse;
        }
        else {
            throw new EntityNotFoundException("Client not found");
        }
    }

    public List<ClientResponse> findAll() {
        Iterable<Client> clients = clientRepository.findAll();
        List<ClientResponse> clientResponseList = clientMapper.entityListToResponseList(clients);

        if (clientResponseList.isEmpty()) {
            throw new EntityNotFoundException("Clients not found");
        }
        return clientMapper.entityListToResponseList(clients);
    }

    public void deleteById(Long id) {
        ClientResponse clientResponse = findOne(id);
        clientRepository.deleteById(clientResponse.getId());
    }

    public List<ProfitableClients> findProfitableClients() {
        List<ProfitableClients> clientsList = clientRepository.findProfitableClients();
        return clientsList;
    }
}
