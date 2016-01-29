package ua.telesens.ostapenko.transportmanager.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.telesens.ostapenko.transportmanager.persistence.model.Station;
import ua.telesens.ostapenko.transportmanager.persistence.repository.StationRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author root
 * @since 28.01.16
 */
@Slf4j
@Service
@Transactional
public class DefaultStationService implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Station add(Station added) {
        log.debug("Adding a new station entry with information: {}", added);
        return stationRepository.save(added);
    }

    @Override
    public List<Station> findAll() {
        log.debug("Find all station");

        List<Station> found = stationRepository.findAll();
        log.debug("Found list station entry: {}", found);
        return stationRepository.findAll();
    }
}
