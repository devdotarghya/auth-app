package org.arghya.auth_app.repository;

import org.arghya.auth_app.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserName(String userName);
}
