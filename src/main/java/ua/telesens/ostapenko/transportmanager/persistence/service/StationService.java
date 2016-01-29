package ua.telesens.ostapenko.transportmanager.persistence.service;

import ua.telesens.ostapenko.transportmanager.persistence.model.Station;

import java.util.List;

/**
 * @author root
 * @since 28.01.16
 */
public interface StationService {

    /**
     * Adds a new station entry.
     *
     * @param added The information of the added station entry.
     * @return The added station entry.
     */
    Station add(Station added);

    /**
     * Finds all station entry.
     *
     * @return The found list station entry.
     */
    List<Station> findAll();
}
