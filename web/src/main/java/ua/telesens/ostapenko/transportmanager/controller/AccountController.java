package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static ua.telesens.ostapenko.transportmanager.controller.EnumView.ACCOUNT;

/**
 * @author root
 * @since 27.01.16
 */

@Slf4j
@Controller
public class AccountController {

    protected static final String VIEW_NAME_ACCOUNT = ACCOUNT.getName();

    @RequestMapping("/account")
    public ModelAndView account(Model model) {
        log.debug("Rendering account page.");

//        User found = TodoUtils.getCurrentUser();
//        log.debug("Found user entry {}", found);

//        model.addAttribute("user", TodoUtils.createDtoUser(found));
        return new ModelAndView(VIEW_NAME_ACCOUNT);

    }
}
