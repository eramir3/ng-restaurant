package com.restaurant.controller;

import com.restaurant.dto.request.BillRequest;
import com.restaurant.dto.response.ResponseMessage;
import com.restaurant.dto.response.BillResponse;
import com.restaurant.entity.BillDetail;
import com.restaurant.exception.RequestFieldValidationException;
import com.restaurant.service.BillService;
import com.restaurant.validator.BillDetailValidator;
import com.restaurant.validator.BillValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/bill")
@CrossOrigin
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillValidator billValidator;

    @Autowired
    private BillDetailValidator billDetailValidator;

    @PostMapping("/save")
    public ResponseEntity<BillResponse> saveOrUpdate(@Valid @RequestBody BillRequest billRequest,
                                               BindingResult result) throws RequestFieldValidationException {

        billValidator.validateFields(result);
        billDetailValidator.validateFields(result);
        BillResponse billResponse =  billService.save(billRequest);
        return new ResponseEntity<>(billResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> findById(@PathVariable Long id) {
        BillResponse billResponse =  billService.findOne(id);
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BillResponse>> findByAll() {
        List<BillResponse> billResponseList =  billService.findAll();
        return new ResponseEntity<>(billResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable Long id) {
        billService.deleteById(id);
        return new ResponseEntity(new ResponseMessage("Bill with id "+id+" was successfully deleted"), HttpStatus.OK);
    }
}
