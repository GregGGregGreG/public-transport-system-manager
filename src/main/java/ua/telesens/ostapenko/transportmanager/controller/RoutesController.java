package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.telesens.ostapenko.transportmanager.persistence.dto.RouteDto;
import ua.telesens.ostapenko.transportmanager.persistence.model.Route;
import ua.telesens.ostapenko.transportmanager.persistence.service.RouteService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.ROUTES;

/**
 * @author root
 * @since 28.01.16
 */

@Slf4j
@Controller
public class RoutesController {

    protected static final String VIEW_NAME_ROUTES = ROUTES.getName();
    private static final String MANY_FORMAT = "%.2f";

    @Autowired
    private RouteService routeService;

    @RequestMapping("/routes")
    public ModelAndView routes(ModelAndView model) {
        log.debug("Rendering routes page.");

        log.debug("Finding routes list entry ");
        List<Route> found = routeService.findAll();
        log.debug("Found routes list entry :{} ", found);

        model.setViewName(VIEW_NAME_ROUTES);
        model.addObject("routes", createRoutesListDot(found));
        return model;
    }

    private List<RouteDto> createRoutesListDot(List<Route> models) {
        Objects.requireNonNull(models);
        return models
                .stream()
                .map(this::createRouteDto)
                .collect(Collectors.toList());
    }

    private RouteDto createRouteDto(Route route) {
        Objects.requireNonNull(route);
        return RouteDto.builder()
                .number(route.getNumber())
                .circular(route.getCircular())
                .price(manyFormat(route.getPrice()))
                .races(route.getRaces())
                .duration(route.getDuration())
                .avgTransport(route.getAvgTransport())
                .countStation(route.getStations().size())
                .build();
    }

    private String manyFormat(double price) {
        return String.format(MANY_FORMAT, price);
    }
}
