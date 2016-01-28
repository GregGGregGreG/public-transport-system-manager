package ua.telesens.ostapenko.transportmanager.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.telesens.ostapenko.transportmanager.persistence.model.Route;
import ua.telesens.ostapenko.transportmanager.persistence.repository.RouteRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author root
 * @since 28.01.16
 */
@Slf4j
@Service
@Transactional
public class DefaultRouteService implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route add(Route added) {
        log.debug("Adding a new route entry with information: {}", added);
        return routeRepository.save(added);
    }

    @Override
    public List<Route> findAll() {
        log.debug("Find all route");

        List<Route> found = routeRepository.findAll();
        log.debug("Found list task entry: {}", found);
        return routeRepository.findAll();
    }
}
