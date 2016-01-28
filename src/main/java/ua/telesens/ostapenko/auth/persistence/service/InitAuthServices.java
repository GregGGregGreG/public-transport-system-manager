package ua.telesens.ostapenko.auth.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.telesens.ostapenko.auth.persistence.EnumRole;
import ua.telesens.ostapenko.auth.persistence.model.Role;
import ua.telesens.ostapenko.auth.persistence.model.User;
import ua.telesens.ostapenko.auth.persistence.repository.RoleRepository;
import ua.telesens.ostapenko.auth.persistence.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * @author root
 * @since 27.01.16
 */
@Transactional
@Service
public class InitAuthServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (userRepository.findByName("greg") == null) {
            User user = new User();
            user.setName("greg");
            user.setEmail("1@mail.ru");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode("123456"));
            user.setEnabled(true);

            Role roleTest = new Role();
            roleTest.setName(EnumRole.USER.getName());
            roleRepository.save(roleTest);

            Set<Role> roles = new HashSet<>();
            roles.add(roleTest);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
