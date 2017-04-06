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
		List<Bet> result = template.query(sql, params, messageMapper);
		
		return result;
	}


	@Override
	public void placeBet(Bet m) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("init", m.getInitiator_id());
        params.put("title", m.getTitle());
        params.put("desc", m.getDescription());
        params.put("pubDate", m.getPub_date());
                
        String sql = "insert into bet(initiator_id, title, description, pub_date) values (:init, :title,:desc, :pubDate)";
		template.update(sql, params);
	}
	
	private RowMapper<Bet> messageMapper = (rs, rowNum) -> {
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

}
