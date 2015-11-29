package ua.telesens.ostapenko.systemimitation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author root
 * @since 29.11.15
 */
@Slf4j
@Controller
public class Index {

    @RequestMapping({"/", "/index"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        log.debug("Rendering index page");
        return modelAndView;
    }
}
