package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.STATIONS;

/**
 * @author root
 * @since 28.01.16
 */
@Slf4j
@Controller
public class StationsController {

    protected static final String VIEW_NAME_STATIONS = STATIONS.getName();

    @RequestMapping("/stations")
    public ModelAndView account(Model model) {
        log.debug("Rendering stations page.");

//        User found = TodoUtils.getCurrentUser();
//        log.debug("Found user entry {}", found);

//        model.addAttribute("user", TodoUtils.createDtoUser(found));
        return new ModelAndView(VIEW_NAME_STATIONS);

    }

}
