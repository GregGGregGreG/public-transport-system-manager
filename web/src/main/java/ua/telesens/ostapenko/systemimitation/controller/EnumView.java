package ua.telesens.ostapenko.systemimitation.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author root
 * @since 01.12.15
 */
@Slf4j
public enum EnumView {

    ERROR("error"),
    P404("p404"),
    HOME("/"),
    INDEX("index"),
    LOGIN("login");

    private final String name;

    EnumView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String redirectTo(EnumView redirect) {
        return "redirect:/" + redirect.getName();
    }

    public static String redirectTo(String redirect) {
        log.debug(" Redirect to: --> " + redirect);
        if (Objects.isNull(redirect)) {
            throw new IllegalArgumentException();
        }

        if (redirect.equals(EnumView.HOME.getName())) {
            return "redirect:/";
        }
        return "redirect:/" + redirect;
    }
}
