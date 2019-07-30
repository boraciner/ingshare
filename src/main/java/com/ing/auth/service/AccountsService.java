package com.ing.auth.service;

import com.ing.auth.model.Accounts;

public interface AccountsService {
	void save(Accounts user);

	Accounts findByUsername(String username);
}
