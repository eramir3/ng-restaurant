package com.restaurant.controller;

import com.restaurant.dto.request.CookRequest;
import com.restaurant.dto.response.ResponseMessage;
import com.restaurant.dto.response.CookResponse;
import com.restaurant.exception.RequestFieldValidationException;
import com.restaurant.service.CookService;
import com.restaurant.validator.CookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/cook")
@CrossOrigin
public class CookController {

    @Autowired
    private CookService cookService;

    @Autowired
    private CookValidator cookValidator;

    @PostMapping("/save")
    public ResponseEntity<CookResponse> saveOrUpdate(@Valid @RequestBody CookRequest cookRequest,
                                                       BindingResult result) throws RequestFieldValidationException {

        cookValidator.validateFields(result);
        CookResponse cookResponse =  cookService.save(cookRequest);

        if (Objects.nonNull(cookRequest.getId())) {
            return new ResponseEntity<>(cookResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(cookResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CookResponse> findById(@PathVariable Long id) {
        CookResponse cookResponse =  cookService.findOne(id);
        return new ResponseEntity<>(cookResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CookResponse>> findByAll() {
        List<CookResponse> cookResponseList =  cookService.findAll();
        return new ResponseEntity<>(cookResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable Long id) {
        cookService.deleteById(id);
        return new ResponseEntity(new ResponseMessage("Cook with id "+id+" was successfully deleted"), HttpStatus.OK);
    }
}
