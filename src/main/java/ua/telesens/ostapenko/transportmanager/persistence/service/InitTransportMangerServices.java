package ua.telesens.ostapenko.transportmanager.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.telesens.ostapenko.transportmanager.persistence.model.Route;
import ua.telesens.ostapenko.transportmanager.persistence.model.Station;
import ua.telesens.ostapenko.transportmanager.persistence.repository.RouteRepository;
import ua.telesens.ostapenko.transportmanager.persistence.repository.StationRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalTime;

/**
 * @author root
 * @since 28.01.16
 */
@Transactional
@Service
public class InitTransportMangerServices {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StationRepository stationRepository;

    @PostConstruct
    public void init() {
        Route route = Route.builder()
                .number("232")
                .circular(true)
                .starting(LocalTime.of(6, 0))
                .ending(LocalTime.of(20, 0))
                .len(LocalTime.of(0, 30))
                .price(5.10)
                .races(15)
                .build();
        routeRepository.save(route);

        Station station = new Station("Topoleck");
        stationRepository.save(station);
    }
}
