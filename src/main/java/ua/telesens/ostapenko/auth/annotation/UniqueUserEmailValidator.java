package ua.telesens.ostapenko.auth.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import ua.telesens.ostapenko.auth.persistence.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author root
 * @since 26.01.16
 */
public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUserEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return userRepository == null || userRepository.findByEmail(email) == null;
    }
}
