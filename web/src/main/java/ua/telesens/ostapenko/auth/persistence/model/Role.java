package ua.telesens.ostapenko.auth.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author root
 * @since 26.01.16
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "users")
public class Role extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
