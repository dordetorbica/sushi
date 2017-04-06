package com.sushi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushi.dao.BetDao;
import com.sushi.dao.UserDao;
import com.sushi.model.LoginResult;
import com.sushi.model.Bet;
import com.sushi.model.User;
import com.sushi.util.PasswordUtil;

@Service
public class SushiService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BetDao betDao;
	
	public List<Bet> getAllBets() {
		return betDao.getAllBets();
	}
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	
	public User getUserbyUsername(String username) {
		return userDao.getUserbyUsername(username);
	}
	
	public void followUser(User follower, User followee) {
		userDao.insertFollower(follower, followee);
	}
	
	public void unfollowUser(User follower, User followee) {
		userDao.deleteFollower(follower, followee);
	}
	
	public boolean isUserFollower(User follower, User followee) {
		return userDao.isUserFollower(follower, followee);
	}
	
	public void addMessage(Bet message) {
		betDao.insertMessage(message);
	}
	
	public LoginResult checkUser(User user) {
		LoginResult result = new LoginResult();
		User userFound = userDao.getUserbyUsername(user.getUsername());
		if(userFound == null) {
			result.setError("Invalid username");
		} else if(!PasswordUtil.verifyPassword(user.getPassword(), userFound.getPassword())) {
			result.setError("Invalid password");
		} else {
			result.setUser(userFound);
		}
		
		return result;
	}
	
	public void registerUser(User user) {
		user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
		userDao.registerUser(user);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setBetDao(BetDao messageDao) {
		this.betDao = messageDao;
	}
}
