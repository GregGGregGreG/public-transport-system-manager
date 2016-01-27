package ua.telesens.ostapenko.transportmanager.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author root
 * @since 03.12.15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Route extends AbstractEntity {

    private static final int MIN_LEN_NAME_NUMBER = 255;

    @Column(nullable = false, unique = true, length = MIN_LEN_NAME_NUMBER)
    private String number;

    @Column(nullable = false)
    private Boolean circular;

    @Column(nullable = false)
    private double starting;

    @Column(nullable = false)
    private double ending;

    @Column(nullable = false)
    private double len;

    @Column(nullable = false)
    private int races;

}
