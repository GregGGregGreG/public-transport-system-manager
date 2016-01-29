package ua.telesens.ostapenko.transportmanager.persistence.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;

import java.time.LocalTime;

/**
 * @author root
 * @since 28.01.16
 */
@ToString
@Builder
@Getter
public class RouteDto {

    private String number;
    private Boolean circular;
    private String price;
    private int races;
    private LocalTime duration;
    private int avgTransport;
    private int countStation;

}
