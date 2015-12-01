package ua.telesens.ostapenko.systemimitation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static ua.telesens.ostapenko.systemimitation.controller.EnumView.*;

/**
 * @author root
 * @since 29.11.15
 */
@Slf4j
@Controller
public class Home {

    protected static final String VIEW_NAME_HOMEPAGE = HOME.getName();

    @RequestMapping(value = "/")
    public ModelAndView home() {
        log.debug("Rendering home page and redirect to login page");
        return new ModelAndView(Login.VIEW_NAME_LOGINPAGE);
    }
}
