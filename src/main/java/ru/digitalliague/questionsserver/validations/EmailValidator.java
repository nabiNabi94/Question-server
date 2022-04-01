package ru.digitalliague.questionsserver.validations;

import ru.digitalliague.questionsserver.annotations.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail,String> {

    /**
     * RFC 5322 email pattern
     * https://www.rfc-editor.org/info/rfc5322
     */
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-] +@ [a-zA-Z0-9.-]+$";


    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return patternMatcher(s,EMAIL_PATTERN);
    }

    public boolean patternMatcher(String string,String regexPattern){
        return Pattern.compile(regexPattern)
                .matcher(string)
                .matches();
    }
}
