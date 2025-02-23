package org.arghya.auth_app.controller;

import org.arghya.auth_app.model.UserRegistrationRequest;
import org.arghya.auth_app.model.UserRegistrationResponse;
import org.arghya.auth_app.service.AuthenticationService;
import org.springframework.web.bind.annotation.RestController;

@RestController("app/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws Exception {
        return authenticationService.register(userRegistrationRequest);
    }
}
