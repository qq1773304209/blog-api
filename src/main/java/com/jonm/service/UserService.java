package com.jonm.service;

import com.jonm.entity.User;

public interface UserService {
	User findUserByUsernameAndPassword(String username, String password);
}
