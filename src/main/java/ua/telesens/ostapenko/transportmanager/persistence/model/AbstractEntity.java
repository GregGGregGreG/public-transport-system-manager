package ua.telesens.ostapenko.transportmanager.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author greg
 * @since 28.06.15
 */
@MappedSuperclass
@Getter
@ToString
@EqualsAndHashCode
public abstract class AbstractEntity {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID id;
}
