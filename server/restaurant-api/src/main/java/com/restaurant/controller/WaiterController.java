package com.restaurant.controller;

import com.restaurant.dto.request.WaiterRequest;
import com.restaurant.dto.response.ResponseMessage;
import com.restaurant.dto.response.WaiterResponse;
import com.restaurant.exception.RequestFieldValidationException;
import com.restaurant.projection.waiter.WaitersTotalBilled;
import com.restaurant.service.WaiterService;
import com.restaurant.validator.WaiterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/waiter")
@CrossOrigin
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private WaiterValidator waiterValidator;

    @PostMapping("/save")
    public ResponseEntity<WaiterResponse> saveOrUpdate(@Valid @RequestBody WaiterRequest waiterRequest,
                                                       BindingResult result) throws RequestFieldValidationException {

        waiterValidator.validateFields(result);
        WaiterResponse waiterResponse =  waiterService.save(waiterRequest);

        if (Objects.nonNull(waiterRequest.getId())) {
            return new ResponseEntity<>(waiterResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(waiterResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaiterResponse> findById(@PathVariable Long id) {
        WaiterResponse waiterResponse =  waiterService.findOne(id);
        return new ResponseEntity<>(waiterResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WaiterResponse>> findByAll() {
        List<WaiterResponse> waiterResponseList =  waiterService.findAll();
        return new ResponseEntity<>(waiterResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable Long id) {
        waiterService.deleteById(id);
        return new ResponseEntity(new ResponseMessage("Waiter with id "+id+" was successfully deleted"), HttpStatus.OK);
    }

    @GetMapping("/totalBilledByWaiter/{year}")
    public ResponseEntity<List<WaitersTotalBilled>> findProfitableClients(@PathVariable String year) {
        List<WaitersTotalBilled> waitersList = waiterService.getTotalBilledByWaiter(year);
        return new ResponseEntity<>(waitersList, HttpStatus.OK);
    }
}
