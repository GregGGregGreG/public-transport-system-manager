package ua.telesens.ostapenko.auth.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.List;

/**
 * @author root
 * @since 26.01.16
 */
@Entity
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "users")
public class Role extends AbstractEntity {

    @Setter
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }


}
