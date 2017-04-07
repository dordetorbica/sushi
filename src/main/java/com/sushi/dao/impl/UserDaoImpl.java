package com.sushi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sushi.dao.UserDao;
import com.sushi.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private NamedParameterJdbcTemplate template;

	@Autowired
	public UserDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public List<User> getAllUsers() {
		Map<String, Object> params = new HashMap<String, Object>();
                
		String sql = "select * from user"; 				
		List<User> result = template.query(sql, params, userMapper);			
		return result;
	}
	
	
	@Override
	public User getUserbyUsername(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", username);
        
		String sql = "SELECT * FROM user WHERE username=:name";
		
        List<User> list = template.query(
                    sql,
                    params,
                    userMapper);
        
        User result = null;
        if(list != null && !list.isEmpty()) {
        	result = list.get(0);
        }
        
		return result;
	}
	
	@Override
	public User getUserbyId(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        
		String sql = "SELECT * FROM user WHERE user_id=:id";
		
        List<User> list = template.query(
                    sql,
                    params,
                    userMapper);
        
        User result = null;
        if(list != null && !list.isEmpty()) {
        	result = list.get(0);
        }
        
		return result;
	}
	

	@Override
	public void registerUser(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", user.getUsername());
        params.put("name",  user.getName());
        params.put("email", user.getEmail());
        params.put("pw", user.getPassword());
        
		String sql = "insert into user (username, name, email, pw) values (:username, :name, :email, :pw)";
		
        template.update(sql,  params);
	}

	private RowMapper<User> userMapper = (rs, rowNum) -> {
		User u = new User();
		
		u.setId(rs.getInt("user_id"));
		u.setEmail(rs.getString("email"));
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("pw"));
		u.setName(rs.getString("name"));
		
		return u;
	};
}
