package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.telesens.ostapenko.auth.persistence.dto.RegistrationForm;
import ua.telesens.ostapenko.auth.persistence.model.User;
import ua.telesens.ostapenko.auth.persistence.service.UserService;
import ua.telesens.ostapenko.transportmanager.persistence.dto.ValidationResponse;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author root
 * @since 27.01.16
 */
@Slf4j
@Controller
public class JoinController {

    private static final String NAME_FIELD = "name";
    private static final String EMAIL_FIELD = "email";
    private static final String VIEW_NAME_JOIN = EnumView.JOIN.getName();

    @Autowired
    private UserService userServices;

    @ModelAttribute("dto")
    public RegistrationForm constructUser() {
        return new RegistrationForm();
    }

    @RequestMapping("/join")
    public ModelAndView showRegister(Principal principal) {
        log.debug("Rendering registration page.");
        if (principal != null) {
            log.debug("User already authorized and request redirect to account page.");
            return new ModelAndView(EnumView.redirectTo(EnumView.FIND));
        }
        return new ModelAndView(VIEW_NAME_JOIN);
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ModelAndView doRegister(@Valid @ModelAttribute("dto") RegistrationForm dto,
                                   BindingResult result,
                                   ModelMap model) {
        log.debug("Adding a new user entry with information: {}", dto);
        if (result.hasErrors()) {
            log.debug("Handle errors user entry: {}", result.hasErrors());
            return new ModelAndView(VIEW_NAME_JOIN);
        }

        User added = userServices.add(dto);
        log.debug("Added a user entry with information: {}", added);
        model.put("success", true);
        return new ModelAndView(EnumView.redirectTo(EnumView.JOIN));
    }

    @RequestMapping(value = "/signup_check/username", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse signUpCheckUserName(@Valid @ModelAttribute("dto") RegistrationForm dto,
                                                  BindingResult result) {
        log.debug("Handle errors user entry: {}", result.hasErrors());
        return getFieldError(result, NAME_FIELD);
    }

    @RequestMapping(value = "/signup_check/email", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse signUpCheckEmail(@Valid @ModelAttribute("dto") RegistrationForm dto,
                                               BindingResult result) {
        log.debug("Handle errors user entry: {}", result.hasErrors());
        return getFieldError(result, EMAIL_FIELD);
    }

    private ValidationResponse getFieldError(BindingResult result, String field) {
        if (result.hasErrors()) {
            log.debug("Find {} message error", field);
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getField() + " - " + error.getDefaultMessage());
                if (error.getField().equals(field)) {
                    return new ValidationResponse(Boolean.FALSE, error.getDefaultMessage());
                }
            }
        }
        return new ValidationResponse(Boolean.TRUE);
    }
}
