package ua.telesens.ostapenko.systemimitation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author greg
 * @since 27.06.15
 */
@Slf4j
@Controller
public class ErrorController {

    @RequestMapping(value = "/error/404", method = RequestMethod.GET)
    public ModelAndView show404Page(HttpServletRequest req, ModelAndView modelAndView) {
        log.debug("Rendering 404 page");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        modelAndView.addObject("errorUrl", requestUri);
        modelAndView.setViewName("p404");
        return modelAndView;
    }

}
