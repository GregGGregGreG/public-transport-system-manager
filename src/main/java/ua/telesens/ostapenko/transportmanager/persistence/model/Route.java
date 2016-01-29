package ua.telesens.ostapenko.transportmanager.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Builder;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
    private LocalTime duration;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int races;

    @Column(nullable = false)
    private int avgTransport;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Station> stations;

    public Route() {
    }

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    @Builder
    public Route(String number, Boolean circular, LocalTime starting, LocalTime ending, LocalTime duration, double price, int races, int avgTransport, List<Station> stations) {
        this.number = number;
        this.circular = circular;
        this.starting = starting;
        this.ending = ending;
        this.duration = duration;
        this.price = price;
        this.races = races;
        this.avgTransport = avgTransport;
        this.stations = stations;
    }
}
