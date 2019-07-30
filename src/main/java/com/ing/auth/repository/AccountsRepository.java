package com.ing.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.auth.model.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	Accounts findByUsername(String username);
}
