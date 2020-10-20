package com.kyle.JavaMovingWars.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kyle.JavaMovingWars.models.User;
import com.kyle.JavaMovingWars.services.UserService;

@Component
public class UserValidator implements Validator {
    
	@Autowired
	private UserService i;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Match");
        }
        if (i.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Duplicate");
		}

    }
}