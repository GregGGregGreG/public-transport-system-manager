package ua.telesens.ostapenko.auth.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.telesens.ostapenko.auth.persistence.model.Role;
import ua.telesens.ostapenko.auth.persistence.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @author root
 * @since 26.01.16
 */
public class SecurityUser extends User implements UserDetails {


    public SecurityUser(User user) {
        if (user != null) {
            this.setName(user.getName());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> userRoles = this.getRoles();

        if (userRoles != null) {
            for (Role role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
