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

	public Bet getBetbyId(int id) {
		return betDao.getBetbyId(id);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public User getUserbyUsername(String username) {
		return userDao.getUserbyUsername(username);
	}

	public User getUserbyId(int id) {
		return userDao.getUserbyId(id);
	}

	public void placeBet(Bet bet) {
		betDao.placeBet(bet);
	}

	public void updateBet(Bet bet) {
		betDao.updateBet(bet);
	}

	public void updateChallenger(Bet bet) {
		betDao.updateChallenger(bet);
	}
	
	public void takeChallenge(int bet_id, int challenger_id) {
		betDao.takeChallenge(bet_id, challenger_id);
	}

	public void closeBet(int bet_id, int winner_id) {
		betDao.closeBet(bet_id, winner_id);
	}

	public LoginResult checkUser(User user) {
		LoginResult result = new LoginResult();
		User userFound = userDao.getUserbyUsername(user.getUsername());
		if (userFound == null) {
			result.setError("Invalid username");
		} else if (!PasswordUtil.verifyPassword(user.getPassword(), userFound.getPassword())) {
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
