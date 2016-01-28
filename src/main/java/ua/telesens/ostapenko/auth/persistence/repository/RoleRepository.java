package ua.telesens.ostapenko.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.telesens.ostapenko.auth.persistence.model.Role;

import java.util.UUID;

/**
 * @author root
 * @since 26.01.16
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);

}
