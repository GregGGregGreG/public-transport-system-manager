package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.ERROR;
import static ua.telesens.ostapenko.transportmanager.controller.EnumView.P404;

/**
 * @author greg
 * @since 27.06.15
 */
@Slf4j
@Controller
public class ErrorsHandler {

    protected static final String VIEW_NAME_P404 = P404.getName();
    protected static final String VIEW_NAME_ERROR = ERROR.getName();

    @RequestMapping(value = "/error/404", method = RequestMethod.GET)
    public ModelAndView show404Page(HttpServletRequest req, ModelAndView modelAndView) {
        log.debug("Rendering 404 page");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        modelAndView.addObject("errorUrl", requestUri);
        modelAndView.setViewName(VIEW_NAME_P404);
        return modelAndView;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView showError(HttpServletRequest req, ModelAndView model) {
        log.debug("Rendering error page");

        final Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        // create a message to be sent back via the model object.
        final String message = MessageFormat.format("{0} returned for {1}", statusCode, requestUri);

        model.addObject("errorMessage", message);
        model.setViewName(VIEW_NAME_ERROR);
        return model;
    }
}
