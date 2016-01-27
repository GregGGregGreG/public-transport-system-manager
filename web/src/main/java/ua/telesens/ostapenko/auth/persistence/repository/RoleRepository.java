package ua.telesens.ostapenko.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.telesens.ostapenko.auth.persistence.model.Role;

/**
 * @author root
 * @since 26.01.16
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
