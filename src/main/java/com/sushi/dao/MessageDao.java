package com.sushi.dao;

import java.util.List;

import com.sushi.model.Message;
import com.sushi.model.User;

public interface MessageDao {
	List<Message> getUserTimelineMessages(User user);
	
	List<Message> getUserFullTimelineMessages(User user);
	
	List<Message> getPublicTimelineMessages();
	
	void insertMessage(Message m);
}
