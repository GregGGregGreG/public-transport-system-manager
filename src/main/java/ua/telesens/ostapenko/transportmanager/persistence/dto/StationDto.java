package ua.telesens.ostapenko.transportmanager.persistence.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 * @author root
 * @since 29.01.16
 */
@ToString
@Builder
@Getter
public class StationDto {

    private String name;
    private int avgCountPassenger;
    private int countRace;

}
