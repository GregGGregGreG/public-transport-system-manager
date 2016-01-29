package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.telesens.ostapenko.transportmanager.alchoritm.deicstra.Graph;
import ua.telesens.ostapenko.transportmanager.persistence.dto.FindDto;
import ua.telesens.ostapenko.transportmanager.persistence.dto.FindResultDto;
import ua.telesens.ostapenko.transportmanager.persistence.model.Route;
import ua.telesens.ostapenko.transportmanager.persistence.model.Station;
import ua.telesens.ostapenko.transportmanager.persistence.service.RouteService;
import ua.telesens.ostapenko.transportmanager.persistence.service.StationService;

import java.util.Collections;
import java.util.List;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.FIND;

/**
 * @author root
 * @since 27.01.16
 */

@Slf4j
@Controller
public class FindController {

    protected static final String VIEW_NAME_FIND = FIND.getName();

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    @RequestMapping("/find")
    public ModelAndView find(Model model) {
        log.debug("Rendering find page.");

//        User found = TodoUtils.getCurrentUser();
//        log.debug("Found user entry {}", found);

//        model.addAttribute("user", TodoUtils.createDtoUser(found));
        return new ModelAndView(VIEW_NAME_FIND);

    }

    @RequestMapping("/find_optimal_cost")
    @ResponseBody
    public FindResultDto find_optimal_cost(@RequestBody FindDto model) {
        log.debug("Rendering find page.");

        List<Station> stations = stationService.findAll();
        Graph graph = new Graph(stations.size());
        for (Station station : stations) {
            graph.addVertex(station.getName());
        }

        List<Route> routes = routeService.findAll();
        List<Station> routeStation;
        for (Route route : routes) {
            routeStation = route.getStations();
            initEdge(graph, routeStation, route);

            if (route.getCircular()) {
                Collections.reverse(routeStation);
                initEdge(graph, routeStation, route);
            }
        }

        double cost = graph.path(model.getStart(), model.getEnd()) / 100;
        String path = graph.getPaths();
        return new FindResultDto(path, cost);
    }

    private void initEdge(Graph graph, List<Station> routeStation, Route route) {
        int start;
        int end;
        for (int i = 0; i < routeStation.size(); i++) {
            for (int j = i; j < routeStation.size() - 1; j++) {
                if (i == routeStation.size() - 1) {
                    continue;
                }
                start = routeStation.indexOf(routeStation.get(i));
                end = routeStation.indexOf(routeStation.get(i));
                // Because  algorithm use int
                // FIXME: 29.01.16 refactor algorithm
                graph.addEdge(start, end, Math.toIntExact((long) (route.getPrice() * 100)));
            }
        }
    }
}
