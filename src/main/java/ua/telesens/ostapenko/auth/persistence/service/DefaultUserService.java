package ua.telesens.ostapenko.auth.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.telesens.ostapenko.auth.persistence.EnumRole;
import ua.telesens.ostapenko.auth.persistence.dto.RegistrationForm;
import ua.telesens.ostapenko.auth.persistence.exception.UserNotFoundException;
import ua.telesens.ostapenko.auth.persistence.model.Role;
import ua.telesens.ostapenko.auth.persistence.model.User;
import ua.telesens.ostapenko.auth.persistence.repository.RoleRepository;
import ua.telesens.ostapenko.auth.persistence.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author root
 * @since 26.01.16
 */
@Slf4j
@Service
@Transactional
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User add(RegistrationForm added) {
        log.debug("Adding a new user entry with information: {}", added);

        Set<Role> roles = new HashSet<>();

        log.debug("Finding a role entry with name: {}", EnumRole.USER);
        Role userRole = roleRepository.findByName(EnumRole.USER.getName());
        log.debug("Found role entry: {}", userRole);

        log.debug("Adding role : {} to new user entry", userRole);
        roles.add(userRole);

        User model = User.getBuilder(added.getName())
                .email(added.getEmail())
                .password(bCrypt(added.getPassword()))
                .roles(roles)
                .enabled(Boolean.TRUE)
                .build();

        return userRepository.save(model);
    }

    @Override
    public User findByName(String name) throws UserNotFoundException {
        log.debug("Finding a user entry with name: {}", name);

        User found = userRepository.findByName(name);
        log.debug("Found user entry: {}", found);

        if (found == null) {
            throw new UserNotFoundException("No user-entry found with name: " + name);
        }

        return found;
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        log.debug("Finding a user entry with email: {}", email);

        User found = userRepository.findByEmail(email);
        log.debug("Found task entry: {}", found);

        if (found == null) {
            throw new UserNotFoundException("No user-entry found with email: " + email);
        }

        return found;
    }

    @Override
    public List<User> finByNameStartingWith(String symbols) {
        log.debug("Find user begins with: {}", symbols);
        List<User> found = userRepository.findByNameStartingWith(symbols);
        log.debug("Found user-list entry: {}", found);

        return found;
    }

    /**
     * Encoding data
     * bcrypt is a key derivation function which is used in this instance as a cryptographic hash function
     *
     * @param password The information of the  encoding password.
     * @return The encoding password.
     */
    private static String bCrypt(String password) {
        log.debug("Coding a new password: {}", password);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
