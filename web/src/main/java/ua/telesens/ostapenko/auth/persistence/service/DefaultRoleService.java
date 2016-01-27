package ua.telesens.ostapenko.auth.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.telesens.ostapenko.auth.persistence.model.Role;
import ua.telesens.ostapenko.auth.persistence.repository.RoleRepository;

import javax.transaction.Transactional;

/**
 * @author root
 * @since 26.01.16
 */
@Slf4j
@Service
@Transactional
public class DefaultRoleService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role add(Role added) {
        log.debug("Adding a new role entry with information: {}", added);
        return roleRepository.save(added);
    }
}
