package com.restaurant.utils;

import com.restaurant.exception.RequestFieldValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class RestaurantUtils {

    public static void mapFieldErrors(BindingResult result) throws RequestFieldValidationException {

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new RequestFieldValidationException(errorMap);
        }
    }
}
