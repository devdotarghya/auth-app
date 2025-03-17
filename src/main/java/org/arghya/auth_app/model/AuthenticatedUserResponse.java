package org.arghya.auth_app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticatedUserResponse {
    private String autheticatedUserName;
}
