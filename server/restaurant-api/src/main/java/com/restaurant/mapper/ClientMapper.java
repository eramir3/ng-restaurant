package com.restaurant.mapper;

import com.restaurant.dto.request.ClientRequest;
import com.restaurant.dto.request.WaiterRequest;
import com.restaurant.dto.response.ClientResponse;
import com.restaurant.entity.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {

    public Client requestToEntity(ClientRequest clientRequest) {
        Client client = new Client();
        client.setId(clientRequest.getId());
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setRemarks(clientRequest.getRemarks());
        return client;
    }

    public ClientResponse entityToResponse(Client client) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setFirstName(client.getFirstName());
        clientResponse.setLastName(client.getLastName());
        clientResponse.setRemarks(client.getRemarks());
        return clientResponse;
    }

    public List<ClientResponse> entityListToResponseList(Iterable<Client> clientList) {
        List<ClientResponse> clientResponseList = new ArrayList<>();
        for (Client client: clientList) {
            clientResponseList.add(this.entityToResponse(client));
        }
        return clientResponseList;
    }
}
