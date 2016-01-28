package ua.telesens.ostapenko.transportmanager.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.Date;

/**
 * @author root
 * @since 28.01.16
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Station extends AbstractEntity {

    private static final int MIN_LEN_NAME = 100;

    @Column(nullable = false, unique = true, length = MIN_LEN_NAME)
    private  String name;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    public Station(String name) {
        this.name = name;
    }
}
