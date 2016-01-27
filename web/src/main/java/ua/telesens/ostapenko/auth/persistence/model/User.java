package ua.telesens.ostapenko.auth.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author root
 * @since 26.01.16
 */
@Data
@Entity
@Table(name = "app_user")
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"roles"})
public class User extends AbstractEntity  {

    public static final int MIN_LENGTH_NAME = 3;
    public static final int MAX_LENGTH_NAME = 50;

    public static final int MIN_LENGTH_PASSWORD = 5;
    public static final int MAX_LENGTH_PASSWORD = 1000;

    public static final int MIN_LENGTH_EMAIL = 1;
    public static final int MAX_LENGTH_EMAIL = 1000;

    @Column(nullable = false, unique = true, length = MAX_LENGTH_NAME)
    private String name;
    @Column(nullable = false, unique = true, length = MAX_LENGTH_EMAIL)
    private String email;
    @Column(nullable = false, length = MAX_LENGTH_PASSWORD)
    private String password;
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Role> roles;

    @Column(nullable = false)
    private Boolean enabled;

    public static Builder getBuilder(String name) {
        return new Builder(name);
    }

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    public static class Builder {

        private User built;

        public Builder(String name) {
            built = new User();
            built.name = name;
        }

        public User build() {
            return built;
        }

        public Builder email(String email) {
            built.email = email;
            return this;
        }

        public Builder password(String password) {
            built.password = password;
            return this;
        }

        public Builder roles(Set<Role> roles) {
            built.roles = roles;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            built.enabled = enabled;
            return this;
        }
    }

}
