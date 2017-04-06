package com.sushi.dao;

import java.util.List;

import com.sushi.model.Bet;
import com.sushi.model.User;

public interface UserDao {

	User getUserbyUsername(String username);
	
	void insertFollower(User follower, User followee);
	
	void deleteFollower(User follower, User followee);
	
	boolean isUserFollower(User follower, User followee);
	
	void registerUser(User user);

	List<User> getAllUsers();
}
