package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.ERROR;
import static ua.telesens.ostapenko.transportmanager.controller.EnumView.P404;

/**
 * @author greg
 * @since 27.06.15
 */
@Slf4j
@ControllerAdvice
public class ErrorsHandler {

    protected static final String VIEW_NAME_P404 = P404.getName();
    protected static final String VIEW_NAME_ERROR = ERROR.getName();

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView show404Page(HttpServletRequest req, NoHandlerFoundException e) {
        log.debug("Rendering 404 page", e);
        ModelAndView mav = new ModelAndView();

        mav.addObject("errorUrl", e.getRequestURL());
        mav.setViewName(VIEW_NAME_P404);
        return mav;
    }

    // Total control - setup a model and return the view name yourself. Or consider
    // subclassing ExceptionHandlerExceptionResolver (see below).
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        log.error("Request: " + req.getRequestURL() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "Sorry error server");
        mav.setViewName(VIEW_NAME_ERROR);
        return mav;
    }
}
