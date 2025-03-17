package org.arghya.auth_app.controller;

import lombok.AllArgsConstructor;
import org.arghya.auth_app.model.AuthenticationRequest;
import org.arghya.auth_app.model.AuthenticationResponse;
import org.arghya.auth_app.model.UserRegistrationRequest;
import org.arghya.auth_app.model.UserRegistrationResponse;
import org.arghya.auth_app.service.AuthenticationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public HttpEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest userRegistrationRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.register(userRegistrationRequest));
    }

    @PostMapping("/login")
    public HttpEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
