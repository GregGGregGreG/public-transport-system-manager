package ua.telesens.ostapenko.transportmanager.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Builder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author root
 * @since 28.01.16
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Station extends AbstractEntity {

    public static final int MAX_LEN_NAME = 100;
    public static final int MAX_AVG_PASSENGER = 1000;

    @Column(nullable = false, unique = true, length = MAX_LEN_NAME)
    private String name;

    @Column(nullable = false, length = MAX_AVG_PASSENGER)
    private int avgCountPassenger;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @ManyToMany(mappedBy = "stations")
    private List<Route> routes;

    public Station() {
    }

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    @Builder
    public Station(String name, int avgCountPassenger) {
        this.name = name;
        this.avgCountPassenger = avgCountPassenger;
    }
}
