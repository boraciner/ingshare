package com.ing.auth.service;

import com.ing.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
