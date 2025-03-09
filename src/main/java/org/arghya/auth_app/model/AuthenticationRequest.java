package org.arghya.auth_app.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;
}
