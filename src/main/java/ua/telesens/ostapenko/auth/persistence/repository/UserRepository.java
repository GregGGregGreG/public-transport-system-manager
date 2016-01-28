package ua.telesens.ostapenko.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.telesens.ostapenko.auth.persistence.model.User;

import java.util.List;
import java.util.UUID;

/**
 * @author root
 * @since 26.01.16
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByName(String name);

    User findByEmail(String email);

    List<User> findByNameStartingWith(String name);

}
