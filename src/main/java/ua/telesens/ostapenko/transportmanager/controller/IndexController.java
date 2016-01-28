package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.*;

/**
 * @author root
 * @since 01.12.15
 */
@Slf4j
@Controller
public class IndexController {

    protected static final String VIEW_NAME_INDEX = INDEX.getName();

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        log.debug("Rendering /index page. --> redirect to \\/: ");
        return new ModelAndView(redirectTo(HomeController.VIEW_NAME_HOMEPAGE));
    }
}
