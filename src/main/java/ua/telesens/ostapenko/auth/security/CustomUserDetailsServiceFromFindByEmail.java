package ua.telesens.ostapenko.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.telesens.ostapenko.auth.persistence.model.User;
import ua.telesens.ostapenko.auth.persistence.repository.UserRepository;

/**
 * @author root
 * @since 26.01.16
 */
@Component
public class CustomUserDetailsServiceFromFindByEmail implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Not found user by email: " + email);
        }

        return new SecurityUser(user);
    }
}
