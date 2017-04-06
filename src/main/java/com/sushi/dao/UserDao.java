package com.sushi.dao;

import java.util.List;

import com.sushi.model.User;

public interface UserDao {

	User getUserbyUsername(String username);
		
	void registerUser(User user);

	List<User> getAllUsers();
}
