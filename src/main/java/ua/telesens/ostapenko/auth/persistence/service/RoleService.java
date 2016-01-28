package ua.telesens.ostapenko.auth.persistence.service;

import ua.telesens.ostapenko.auth.persistence.model.Role;

/**
 * @author root
 * @since 26.01.16
 */
public interface RoleService {

    /**
     * Adds a new role entry.
     *
     * @param added  The information of the added role entry.
     * @return The added role entry.
     */
    Role add(Role added);
}
