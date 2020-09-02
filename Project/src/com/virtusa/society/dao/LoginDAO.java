package com.virtusa.society.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.virtusa.society.model.Login;

public class LoginDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public LoginDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null) {
			jdbcConnection.close();
		}
	}
	
	public boolean customerLogin(Login login) throws SQLException {		
		connect();
		boolean b=false;
		String sql="select name from user where email=? and password=?";
		PreparedStatement statement=jdbcConnection.prepareStatement(sql);
		statement.setString(1, login.getEmail());
		statement.setString(2,login.getPassword());
		ResultSet rs=statement.executeQuery();
		if(rs.next()){
			login.setUsername(rs.getString("name"));
			b=true;
		}
		statement.close();
		disconnect();
		return b;
	}
	public boolean employeeLogin(Login login) throws SQLException {		
		connect();
		boolean b=false;
		String sql="select ename from employee where emailid=? and password=?";
		PreparedStatement statement=jdbcConnection.prepareStatement(sql);
		statement.setString(1, login.getEmail());
		statement.setString(2,login.getPassword());
		ResultSet rs=statement.executeQuery();
		if(rs.next()){
			login.setUsername(rs.getString("ename"));
			b=true;
		}
		statement.close();
		disconnect();
		return b;
	}
	public boolean adminLogin(Login login) throws SQLException {		
		connect();
		boolean b=false;
		String sql="select name from admin where email=? and password=?";
		PreparedStatement statement=jdbcConnection.prepareStatement(sql);
		statement.setString(1, login.getEmail());
		statement.setString(2,login.getPassword());
		ResultSet rs=statement.executeQuery();
		if(rs.next()){
			login.setUsername(rs.getString("name"));
			b=true;
		}
		statement.close();
		disconnect();
		return b;
	}
}
