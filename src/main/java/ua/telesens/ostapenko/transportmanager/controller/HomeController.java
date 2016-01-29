package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.*;

/**
 * @author root
 * @since 29.11.15
 */
@Slf4j
@Controller
public class HomeController {

    protected static final String VIEW_NAME_HOMEPAGE = HOME.getName();



    @RequestMapping(value = "/")
    public ModelAndView home() {
        log.debug("Rendering home page and redirect to login page");
        return new ModelAndView(redirectTo(RoutesController.VIEW_NAME_ROUTES));
    }
}
