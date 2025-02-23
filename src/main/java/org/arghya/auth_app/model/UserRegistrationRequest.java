package org.arghya.auth_app.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Data
@Setter
@Getter
@Builder
public class UserRegistrationRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

}
