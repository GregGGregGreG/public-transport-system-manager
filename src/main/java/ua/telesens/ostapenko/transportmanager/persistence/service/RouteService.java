package ua.telesens.ostapenko.transportmanager.persistence.service;

import ua.telesens.ostapenko.transportmanager.persistence.model.Route;

import java.util.List;

/**
 * @author root
 * @since 28.01.16
 */
public interface RouteService {

    /**
     * Adds a new route entry.
     *
     * @param added The information of the added route entry.
     * @return The added route entry.
     */
    Route add(Route added);

    /**
     * Finds all route entry.
     *
     * @return  The found list route entry.
     */
    List<Route> findAll();
}
