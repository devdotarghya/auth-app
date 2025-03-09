package org.arghya.auth_app.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
public class UserRegistrationRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;

}
