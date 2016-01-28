package ua.telesens.ostapenko.transportmanager.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author root
 * @since 01.12.15
 */
@Slf4j
public enum EnumView {

    ERROR("error", "/error"),
    P404("p404", "/404"),
    HOME("/", "/"),
    INDEX("index", "/index"),
    LOGIN("login", "/login"),
    // Find short way
    FIND("find", "/find"),
    ROUTES("routes", "/routes"),
    STATIONS("stations", "/stations"),
    JOIN("join", "/join");

    private final String name;
    private final String path;

    EnumView(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public static String redirectTo(EnumView redirect) {
        return "redirect:" + redirect.getPath();
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
