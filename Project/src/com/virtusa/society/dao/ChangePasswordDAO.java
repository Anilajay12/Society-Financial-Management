package com.virtusa.society.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.virtusa.society.model.ChangePassword;

public class ChangePasswordDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ChangePasswordDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean changePassword(ChangePassword change) throws SQLException {
		connect();
		boolean status = false;
		String tablename = change.getRole();
		if (tablename.equals("user")) {
			String query = "select password from user where email=?";
			PreparedStatement statement = jdbcConnection.prepareStatement(query);
			statement.setString(1, change.getName());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(change.getCurrentPassword())) {
					String sql = "update user set password=? where email=?";
					statement = jdbcConnection.prepareStatement(sql);
					statement.setString(1, change.getNewPassword());
					statement.setString(2, change.getName());
					status = statement.executeUpdate() > 0;
				}
				statement.close();
			}
		} else if (tablename.equals("employee")) {
			String query = "select password from employee where emailid=?";
			PreparedStatement statement = jdbcConnection.prepareStatement(query);
			statement.setString(1, change.getName());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(change.getCurrentPassword())) {
					String sql = "update employee set password=? where emailid=?";
					statement = jdbcConnection.prepareStatement(sql);
					statement.setString(1, change.getNewPassword());
					statement.setString(2, change.getName());
					
					status = statement.executeUpdate() >0;
				}
				statement.close();
			}
		} else if (tablename.equals("admin")) {
			String query = "select password from admin where email=?";
			PreparedStatement statement = jdbcConnection.prepareStatement(query);
			statement.setString(1, change.getName());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(change.getCurrentPassword())) {
					String sql = "update admin set password=? where email=?";
					statement = jdbcConnection.prepareStatement(sql);
					statement.setString(1, change.getNewPassword());
					statement.setString(2, change.getName());
					status = statement.executeUpdate() > 0;
				}
				statement.close();
			}
		}
		disconnect();
		return status;
	}

}
