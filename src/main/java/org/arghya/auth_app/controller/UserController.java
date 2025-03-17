package org.arghya.auth_app.controller;

import org.arghya.auth_app.model.AuthenticatedUserResponse;
import org.arghya.auth_app.model.AuthenticationResponse;
import org.arghya.auth_app.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public HttpEntity<AuthenticatedUserResponse> getAuthenticatedCustomer() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(AuthenticatedUserResponse.builder()
                        .autheticatedUserName(user.getFirstName().concat(" ").concat(user.getLastName()))
                .build());
    }
}
