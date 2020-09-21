package com.restaurant.exception;

import com.restaurant.dto.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Exception.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseMessage exceptionMessage = new ResponseMessage("Invalid field values");
        return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request)  {
        ResponseMessage exceptionMessage = new ResponseMessage("Invalid field values");
        return new ResponseEntity(exceptionMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleDataRepositoryException(DataRepositoryException ex, WebRequest request)  {
        ResponseMessage exceptionMessage = new ResponseMessage("Invalid field values");
        return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ResponseMessage exceptionMessage = new ResponseMessage(ex.getMessage());
        return new ResponseEntity(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRequestFieldValidationException(RequestFieldValidationException ex, WebRequest request) {
        LOGGER.error("Request field error", ex);
        return new ResponseEntity(ex.getFieldErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> exception(Exception exception, WebRequest request) {
        ResponseMessage exceptionMessage = new ResponseMessage("Unexpected failure. Try later.");
        return new ResponseEntity(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
