package org.arghya.auth_app.service;

import lombok.AllArgsConstructor;
import org.arghya.auth_app.entity.UserEntity;
import org.arghya.auth_app.model.UserRegistrationRequest;
import org.arghya.auth_app.model.UserRegistrationResponse;
import org.arghya.auth_app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws Exception {

        UserEntity userEntity = UserEntity.builder()
                .userName(userRegistrationRequest.getUserName())
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .build();
        UserEntity savedUser = userRepository.save(userEntity);
        return Optional.ofNullable(userEntity)
                .map(UserRegistrationResponse::new)
                .orElseThrow(()-> new Exception("user could not be registered"));
    }
}
