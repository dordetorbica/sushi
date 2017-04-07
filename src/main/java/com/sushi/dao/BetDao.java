package com.sushi.dao;

import java.util.List;

import com.sushi.model.Bet;

public interface BetDao {	
	void placeBet(Bet bet);

	List<Bet> getAllBets();
	
	Bet getBetbyId(int id);

	void takeChallenge(int bet_id, int challenger_id);

	void closeBet(int bet_id, int winner_id);

	void updateBet(Bet bet);

	void updateChallenger(Bet bet);
		
}
