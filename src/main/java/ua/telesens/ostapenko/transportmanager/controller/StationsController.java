package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.telesens.ostapenko.transportmanager.persistence.dto.StationDto;
import ua.telesens.ostapenko.transportmanager.persistence.model.Station;
import ua.telesens.ostapenko.transportmanager.persistence.service.StationService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.STATIONS;

/**
 * @author root
 * @since 28.01.16
 */
@Slf4j
@Controller
public class StationsController {

    protected static final String VIEW_NAME_STATIONS = STATIONS.getName();

    @Autowired
    private StationService stationService;

    @RequestMapping("/stations")
    public ModelAndView stations(ModelAndView model) {
        log.debug("Rendering stations page.");

        log.debug("Finding stations list entry ");
        List<Station> found = stationService.findAll();
        log.debug("Found stations list entry :{} ", found);

        model.setViewName(VIEW_NAME_STATIONS);
        model.addObject("stations", createStationListDot(found));
        return model;
    }

    private List<StationDto> createStationListDot(List<Station> models) {
        Objects.requireNonNull(models);
        return models
                .stream()
                .map(this::createStationDto)
                .collect(Collectors.toList());
    }

    private StationDto createStationDto(Station station) {
        Objects.requireNonNull(station);
        return StationDto.builder()
                .name(station.getName())
                .avgCountPassenger(station.getAvgCountPassenger())
                .countRace(station.getRoutes().size())
                .build();
    }
}
