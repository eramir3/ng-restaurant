package com.restaurant.controller;

import com.restaurant.dto.request.ClientRequest;
import com.restaurant.projection.client.ProfitableClients;
import com.restaurant.dto.response.ResponseMessage;
import com.restaurant.dto.response.ClientResponse;
import com.restaurant.exception.RequestFieldValidationException;
import com.restaurant.service.ClientService;
import com.restaurant.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/client")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientValidator clientValidator;

    @PostMapping("/save")
    public ResponseEntity<ClientResponse> saveOrUpdate(@Valid @RequestBody ClientRequest clientRequest,
                                                       BindingResult result) throws RequestFieldValidationException {

        clientValidator.validateFields(result);
        ClientResponse clientResponse =  clientService.save(clientRequest);

        if (Objects.nonNull(clientRequest.getId())) {
            return new ResponseEntity<>(clientResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        ClientResponse clientResponse =  clientService.findOne(id);
        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientResponse>> findByAll() {
        List<ClientResponse> clientResponseList =  clientService.findAll();
        return new ResponseEntity<>(clientResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return new ResponseEntity(new ResponseMessage("Client with id "+id+" was successfully deleted"), HttpStatus.OK);
    }

    @GetMapping("/profitableClients")
    public ResponseEntity<List<ProfitableClients>> findProfitableClients() {
        System.out.println("aaaaaaaa");
        List<ProfitableClients> clientsList = clientService.findProfitableClients();
        return new ResponseEntity<>(clientsList, HttpStatus.OK);
    }
}
