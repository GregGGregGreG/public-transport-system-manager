package ua.telesens.ostapenko.transportmanager.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author root
 * @since 03.12.15
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Route extends AbstractEntity {

    private static final int MIN_LEN_NAME_NUMBER = 100;

    @Column(nullable = false, unique = true, length = MIN_LEN_NAME_NUMBER)
    private String number;

    @Column(nullable = false)
    private Boolean circular;

    @Column(nullable = false)
    private LocalTime starting;

    @Column(nullable = false)
    private LocalTime ending;

    @Column(nullable = false)
    private LocalTime len;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int races;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    @Builder
    private Route(String number,
                 Boolean circular,
                 LocalTime starting,
                 LocalTime ending,
                 LocalTime len,
                 double price,
                 int races) {
        this.number = number;
        this.circular = circular;
        this.starting = starting;
        this.ending = ending;
        this.len = len;
        this.price = price;
        this.races = races;
    }
}
