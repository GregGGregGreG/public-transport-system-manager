package ua.telesens.ostapenko.systemimitation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static ua.telesens.ostapenko.systemimitation.controller.EnumView.*;

/**
 * @author greg
 * @since 27.06.15
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class Login {

    protected static final String VIEW_NAME_LOGINPAGE = LOGIN.getName();

    @RequestMapping()
    public ModelAndView login(Principal principal) {
        log.debug("Rendering login page.");
        if (principal != null) {
            log.debug("User already authorized and request redirect to account page.");
            return new ModelAndView("account");
        }
        log.debug("User not authorized and request redirect to home page.");
        return new ModelAndView(redirectTo(Home.VIEW_NAME_HOMEPAGE));
    }
}
