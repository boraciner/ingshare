package com.ing.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.auth.model.Accounts;
import com.ing.auth.repository.AccountsRepository;
@Service
public class AccountsServiceImpl implements AccountsService {

	@Autowired 
	AccountsRepository repo;
	
	@Override
	public void save(Accounts user) {
		repo.save(user);
	}

	@Override
	public Accounts findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
