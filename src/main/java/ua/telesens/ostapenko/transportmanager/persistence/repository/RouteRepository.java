package ua.telesens.ostapenko.transportmanager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.telesens.ostapenko.transportmanager.persistence.model.Route;

import java.util.UUID;

/**
 * @author root
 * @since 28.01.16
 */
public interface RouteRepository extends JpaRepository<Route, UUID> {


}
