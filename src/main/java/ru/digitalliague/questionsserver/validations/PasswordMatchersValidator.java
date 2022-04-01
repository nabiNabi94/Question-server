package ru.digitalliague.questionsserver.validations;

import ru.digitalliague.questionsserver.annotations.PasswordMatchers;
import ru.digitalliague.questionsserver.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchersValidator implements ConstraintValidator<PasswordMatchers,Object> {
    @Override
    public void initialize(PasswordMatchers constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest signupRequest = (SignupRequest) obj;
        return signupRequest.getPassword().equals(signupRequest.getConformPassword());
    }
}
