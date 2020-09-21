package com.restaurant.validator;

import com.restaurant.exception.RequestFieldValidationException;
import com.restaurant.utils.RestaurantUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BillDetailValidator implements Validator {

    public void validateFields(BindingResult result) throws RequestFieldValidationException {
        RestaurantUtils.mapFieldErrors(result);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //return UserRequestDTO.class.equals(aClass);
        return true;
    }

    @Override
    public void validate(Object object, Errors errors) {

//        UserRequestDTO user = (UserRequestDTO) object;

//        if (user.getPassword().length() < 6) {
//            errors.rejectValue("password", "Length", "Password must be at least 6 -12 characters");
//        }

//        if (user.getPassword() != null) {
//            if (!user.getPassword().equals(user.getConfirmPassword())) {
//                errors.rejectValue("confirmPassword", "Match", "Passwords must match");
//            }
//        }

        //confirmPassword

    }
}
