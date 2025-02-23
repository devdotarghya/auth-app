package org.arghya.auth_app.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ServiceErrorResponse {

    private String errorMessage;
}
