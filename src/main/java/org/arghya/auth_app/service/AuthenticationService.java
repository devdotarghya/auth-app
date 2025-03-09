package org.arghya.auth_app.service;

import lombok.AllArgsConstructor;
import org.arghya.auth_app.entity.UserEntity;
import org.arghya.auth_app.model.*;
import org.arghya.auth_app.repository.UserRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;


    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws Exception {

        UserEntity userEntity = UserEntity.builder()
                .userName(userRegistrationRequest.getUserName())
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .role(userRegistrationRequest.getRole())
                .build();
        UserEntity savedUser = userRepository.save(userEntity);
        return Optional.ofNullable(savedUser)
                .map(UserRegistrationResponse::new)
                .orElseThrow(()-> new Exception("user could not be registered"));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            String accessToken = jwtService.generateToken((User)authentication.getPrincipal());
            return new AuthenticationResponse(accessToken);
        } else {
            throw new Exception("User not authenticated");
        }
    }
}
