package ua.telesens.ostapenko.systemimitation.persistence.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author greg
 * @since 28.06.15
 */
@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;
}
