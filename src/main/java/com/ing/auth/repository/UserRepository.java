package com.ing.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
