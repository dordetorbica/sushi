package com.sushi.dao;

import java.util.List;

import com.sushi.model.Bet;
import com.sushi.model.User;

public interface BetDao {	
	void insertMessage(Bet m);

	List<Bet> getAllBets();
}
