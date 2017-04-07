package com.sushi.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

@Configuration
public class DatabaseConfig {

	
	
	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");

		dataSource.setUrl("jdbc:hsqldb:file:testdb");

		dataSource.setUsername("SA");

		dataSource.setPassword("");
		Resource rc1 = new ClassPathResource("sql/create-db.sql");
		Resource rc2 = new ClassPathResource("sql/insert-data.sql");
		try {
			ScriptUtils.executeSqlScript(dataSource.getConnection(), rc1);
			ScriptUtils.executeSqlScript(dataSource.getConnection(), rc2);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		
		return dataSource;
	}

}
