package ua.telesens.ostapenko.auth.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import ua.telesens.ostapenko.auth.persistence.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author root
 * @since 26.01.16
 */
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UniqueUserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return userRepository == null || userRepository.findByName(name) == null;
    }
}
