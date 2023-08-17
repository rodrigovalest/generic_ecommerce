package com.learn.ecommerce.repository;

import com.learn.ecommerce.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
    UserModel findByUsernameAndPassword(String username, String password);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
