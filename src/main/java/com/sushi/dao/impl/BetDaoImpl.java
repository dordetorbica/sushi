package com.sushi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sushi.dao.BetDao;
import com.sushi.model.Bet;
import com.sushi.model.User;

@SuppressWarnings("unused")
@Repository
public class BetDaoImpl implements BetDao {
	
	private NamedParameterJdbcTemplate template;

	@Autowired
	public BetDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public List<Bet> getAllBets() {
		Map<String, Object> params = new HashMap<String, Object>();
                
		String sql = "select * from bet order by pub_date"; 				
		List<Bet> result = template.query(sql, params, betMapper);
		
		return result;
	}
	
	@Override
	public Bet getBetbyId(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
                
		String sql = "select * from bet where bet_id=:id"; 		
		
        List<Bet> list = template.query(
                    sql,
                    params,
                    betMapper);
        
        Bet result = null;
        if(list != null && !list.isEmpty()) {
        	result = list.get(0);
        }
		
		return result;
	}


	@Override
	public void placeBet(Bet bet) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("init", bet.getInitiator_id());
        params.put("title", bet.getTitle());
        params.put("desc", bet.getDescription());
        // params.put("pubdate", bet.getPub_date());
        
        String sql = "INSERT into bet(initiator_id, title, description, pub_date)  VALUES (:init,:title,:desc,current_timestamp)";
		template.update(sql, params);
	}
	
	@Override
	public void takeChallenge(int bet_id, int challenger_id) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("bet_id", bet_id);
        params.put("ch", challenger_id);
             
        String sql = "UPDATE bet SET challenger_id =:ch WHERE bet_id = :bet_id";        		
		template.update(sql, params);
		
	}

	@Override
	public void closeBet(int bet_id, int winner_id) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("bet_id", bet_id);
        params.put("w", winner_id);
             
        String sql = "UPDATE bet SET winner_id =:w WHERE bet_id = :bet_id";        		
		template.update(sql, params);
		
	}
	
	private RowMapper<Bet> betMapper = (rs, rowNum) -> {
		Bet bet = new Bet();
		bet.setBet_id(rs.getInt("bet_id"));
		bet.setInitiator_id(rs.getInt("initiator_id"));
		bet.setChallenger_id(rs.getInt("challenger_id"));  
		bet.setTitle(rs.getString("title"));
		bet.setDescription(rs.getString("description"));
		bet.setPub_date(rs.getTimestamp("pub_date"));
		bet.setResolved_date(rs.getTimestamp("resolved_date"));
				  		 		   	
		return bet;
	};

	@Override
	public void updateBet(Bet bet) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("bet_id", bet.getBet_id());
        params.put("title", bet.getTitle());
        params.put("desc", bet.getDescription());       
        
        String sql = "UPDATE bet SET title=:title,description=:desc WHERE bet_id = :bet_id";
		template.update(sql, params);
		
	}

	@Override
	public void updateChallenger(Bet bet) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("bet_id", bet.getBet_id());
        params.put("challenger_id", bet.getChallenger_id());        
        
        String sql = "UPDATE bet SET challenger_id=:challenger_id WHERE bet_id = :bet_id";
		template.update(sql, params);
		
	}
}
