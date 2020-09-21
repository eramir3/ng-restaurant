package com.restaurant.validator;

import com.restaurant.exception.RequestFieldValidationException;
import com.restaurant.utils.RestaurantUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;


@Component
public class WaiterValidator {

    public void validateFields(BindingResult result) throws RequestFieldValidationException {
        RestaurantUtils.mapFieldErrors(result);
    }

}
