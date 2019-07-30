package com.ing.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
