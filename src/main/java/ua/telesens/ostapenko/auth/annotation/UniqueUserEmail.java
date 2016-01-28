package ua.telesens.ostapenko.auth.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author root
 * @since 26.01.16
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueUserEmailValidator.class})
public @interface UniqueUserEmail {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
