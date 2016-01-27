package ua.telesens.ostapenko.auth.persistence.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author root
 * @since 26.01.16
 */
@MappedSuperclass
@Data
public class AbstractEntity {
    @Id
    @GeneratedValue
    private UUID id;
}
