package com.sushi.dao;

import java.util.List;

import com.sushi.model.Bet;

public interface BetDao {	
	void insertMessage(Bet m);

	List<Bet> getAllBets();
	
	Bet getBetbyId(int id);
}
