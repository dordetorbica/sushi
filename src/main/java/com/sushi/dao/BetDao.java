package com.sushi.dao;

import java.util.List;

import com.sushi.model.Bet;
import com.sushi.model.User;

public interface BetDao {
	List<Bet> getUserTimelineMessages(User user);
	
	List<Bet> getUserFullTimelineMessages(User user);
	
	List<Bet> getPublicTimelineMessages();
	
	void insertMessage(Bet m);
}
