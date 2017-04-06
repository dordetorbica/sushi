package com.sushi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bet {
	
	  private int bet_id;
	  private int initiator_id;
	  private int challenger_id;
	  private String title; 
	  private String description;  
	  private Date pub_date; 
	  private Date resolved_date;
	public int getBet_id() {
		return bet_id;
	}
	public void setBet_id(int bet_id) {
		this.bet_id = bet_id;
	}
	public int getInitiator_id() {
		return initiator_id;
	}
	public void setInitiator_id(int initiator_id) {
		this.initiator_id = initiator_id;
	}
	public int getChallenger_id() {
		return challenger_id;
	}
	public void setChallenger_id(int challenger_id) {
		this.challenger_id = challenger_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPub_date() {
		return pub_date;
	}
	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}
	public Date getResolved_date() {
		return resolved_date;
	}
	public void setResolved_date(Date resolved_date) {
		this.resolved_date = resolved_date;
	}
}
