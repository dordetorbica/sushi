package com.sushi.dao;

import java.util.List;

import com.sushi.model.User;

public interface UserDao {

	User getUserbyUsername(String username);

	User getUserbyId(int id);
		
	void registerUser(User user);

	List<User> getAllUsers();
}
