package ua.telesens.ostapenko.systemimitation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static ua.telesens.ostapenko.systemimitation.controller.EnumView.redirectTo;

/**
 * @author root
 * @since 01.12.15
 */
@Slf4j
@Controller
public class Index {

    @RequestMapping(value = "/index")
    public ModelAndView home() {
        log.debug("Rendering /index page. --> redirect to \\/: ");
        return new ModelAndView(redirectTo(Home.VIEW_NAME_HOMEPAGE));
    }
}
