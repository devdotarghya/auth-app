package org.arghya.auth_app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.arghya.auth_app.model.Role;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;

@Entity(name = "USER_DETAIL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Role role;

}
