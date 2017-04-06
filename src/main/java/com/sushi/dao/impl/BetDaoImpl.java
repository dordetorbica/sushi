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
import com.sushi.util.GravatarUtil;

@Repository
public class BetDaoImpl implements BetDao {
	
	private static final String GRAVATAR_DEFAULT_IMAGE_TYPE = "monsterid";
	private static final int GRAVATAR_SIZE = 48;
	private NamedParameterJdbcTemplate template;

	@Autowired
	public BetDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public List<Bet> getUserTimelineMessages(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
        
		String sql = "select message.*, user.* from message, user where " +
				"user.user_id = message.author_id and user.user_id = :id " +
				"order by message.pub_date desc";
		List<Bet> result = template.query(sql, params, messageMapper);
		
		return result;
	}

	@Override
	public List<Bet> getUserFullTimelineMessages(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
        
		String sql = "select message.*, user.* from message, user " +
				"where message.author_id = user.user_id and ( " +
				"user.user_id = :id or " +
				"user.user_id in (select followee_id from follower " +
                                    "where follower_id = :id))" +
                "order by message.pub_date desc";
		List<Bet> result = template.query(sql, params, messageMapper);
		
		return result;
	}

	@Override
	public List<Bet> getPublicTimelineMessages() {
		Map<String, Object> params = new HashMap<String, Object>();
        
		String sql = "select message.*, user.* from message, user " +
				"where message.author_id = user.user_id " +
				"order by message.pub_date desc";
		List<Bet> result = template.query(sql, params, messageMapper);
		
		return result;
	}

	@Override
	public void insertMessage(Bet m) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", m.getUserId());
        params.put("text", m.getText());
        params.put("pubDate", m.getPubDate());
        
        String sql = "insert into message (author_id, text, pub_date) values (:userId, :text, :pubDate)";
		template.update(sql, params);
	}
	
	private RowMapper<Bet> messageMapper = (rs, rowNum) -> {
		Bet m = new Bet();
		
		m.setId(rs.getInt("message_id"));
		m.setUserId(rs.getInt("author_id"));
		m.setUsername(rs.getString("username"));
		m.setText(rs.getString("text"));
		m.setPubDate(rs.getTimestamp("pub_date"));
		m.setGravatar(GravatarUtil.gravatarURL(rs.getString("email"), GRAVATAR_DEFAULT_IMAGE_TYPE, GRAVATAR_SIZE));
		
		return m;
	};

}
