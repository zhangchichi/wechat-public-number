package com.cc.wechat.wxpn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
//	@PostConstruct
//	public void initialize(){
//		try {
//			Connection connection = dataSource.getConnection();
//			Statement statement = connection.createStatement();
//			statement.execute("DROP TABLE IF EXISTS UserLogin");
//			statement.executeUpdate(
//					"CREATE TABLE UserLogin(" +
//					"id INTEGER Primary key, " +
//					"userName varchar(30) not null," +
//					"password varchar(30) not null," +
//					"firstName varchar(30) not null, " +
//					"lastName varchar(30) not null," +
//					"email varchar(100) not null," +
//					"mobile varchar(30) not null)"
//					);
//			statement.executeUpdate(
//					"INSERT INTO UserLogin " +
//					"(userName,password,firstName,lastName,email,mobile) " +
//					"VALUES " + "('bharat0126','dbase123','Bharat','Verma',"
//						+ " 'bharatverma2488@gmail.com','8861456151')"
//					);
//			statement.close();
//			connection.close();
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
