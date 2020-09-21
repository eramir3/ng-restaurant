package com.restaurant.controller;

import com.restaurant.dto.request.DiningTableRequest;
import com.restaurant.dto.response.ResponseMessage;
import com.restaurant.dto.response.DiningTableResponse;
import com.restaurant.exception.RequestFieldValidationException;
import com.restaurant.service.DiningTableService;
import com.restaurant.validator.DiningTableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/table")
@CrossOrigin
public class DiningTableController {

    @Autowired
    private DiningTableService diningTableService;

    @Autowired
    private DiningTableValidator diningTableValidator;

    @PostMapping("/save")
    public ResponseEntity<DiningTableResponse> saveOrUpdate(@Valid @RequestBody DiningTableRequest diningTableRequest,
                                                       BindingResult result) throws RequestFieldValidationException {

        diningTableValidator.validateFields(result);
        DiningTableResponse diningTableResponse =  diningTableService.save(diningTableRequest);

        if (Objects.nonNull(diningTableRequest.getId())) {
            return new ResponseEntity<>(diningTableResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(diningTableResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiningTableResponse> findById(@PathVariable Long id) {
        DiningTableResponse diningTableResponse =  diningTableService.findOne(id);
        return new ResponseEntity<>(diningTableResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiningTableResponse>> findByAll() {
        List<DiningTableResponse> diningTableResponseList =  diningTableService.findAll();
        return new ResponseEntity<>(diningTableResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable Long id) {
        diningTableService.deleteById(id);
        return new ResponseEntity(new ResponseMessage("Table with id "+id+" was successfully deleted"), HttpStatus.OK);
    }
}
