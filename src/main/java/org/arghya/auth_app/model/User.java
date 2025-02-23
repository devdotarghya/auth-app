package org.arghya.auth_app.model;

import jakarta.persistence.*;
import lombok.*;
import org.arghya.auth_app.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public User(UserEntity userEntity) {
        this.userName = userEntity.getUserName();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.password = userEntity.getPassword();
        this.role = userEntity.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getUsername() {
        return userName;
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
