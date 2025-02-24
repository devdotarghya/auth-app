package org.arghya.auth_app.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.arghya.auth_app.model.Permission.*;

public enum Role {

    USER(Collections.emptySet()),
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_WRITE,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            CUSTOMER_READ,
            CUSTOMER_WRITE,
            CUSTOMER_UPDATE,
            CUSTOMER_DELETE
    )),
    CUSTOMER(Set.of(CUSTOMER_READ,
            CUSTOMER_WRITE,
            CUSTOMER_UPDATE,
            CUSTOMER_DELETE));

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }

}
