package ua.telesens.ostapenko.transportmanager.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author root
 * @since 29.01.16
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
public class FindResultDto {

    private String path;
    private double cost;

}
