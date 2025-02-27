package org.arghya.auth_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.arghya.auth_app.entity.UserEntity;

@Getter
public class UserRegistrationResponse {

    private String userName;
    private String firstName;
    private String lastName;

    public UserRegistrationResponse(UserEntity userDetails) {
        this.userName = userDetails.getUserName();
        this.firstName = userDetails.getFirstName();
        this.lastName = userDetails.getLastName();
    }

}
