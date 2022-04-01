package ru.digitalliague.questionsserver.annotations;

import ru.digitalliague.questionsserver.validations.PasswordMatchersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(value = {ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordMatchersValidator.class)
public @interface PasswordMatchers {
    String message() default "Passwords is not matchers";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
