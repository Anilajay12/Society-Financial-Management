package com.virtusa.society.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.virtusa.society.model.Feedback;

public class FeedbackDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public FeedbackDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insertFeedback(Feedback feedback) throws SQLException {
		String sql = "INSERT INTO contact (name, emailid, message) VALUES (?, ?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, feedback.getName());
		statement.setString(2, feedback.getEmailId());
		statement.setString(3, feedback.getMessage());
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<Feedback> listAllFeedbacks() throws SQLException {
		List<Feedback> listFeedbacks = new ArrayList<>();

		String sql = "SELECT * FROM contact";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String emailid = resultSet.getString("emailid");
			String message = resultSet.getString("message");
			Timestamp RequestedTime=resultSet.getTimestamp("RequestedTime");
			Feedback record = new Feedback(id,name,emailid,message,RequestedTime);
			listFeedbacks.add(record);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listFeedbacks;
	}

	public boolean deleteFeedback(Feedback feedback) throws SQLException {
		String sql = "DELETE FROM contact where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, feedback.getId());
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public Feedback getfeedback(int id) throws SQLException {
		Feedback feedback = null;
		String sql = "SELECT * FROM contact WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String name = resultSet.getString("name");
			String emailid = resultSet.getString("emailid");
			String message = resultSet.getString("message");
			feedback = new Feedback(name, emailid, message);
		}

		resultSet.close();
		statement.close();

		return feedback;
	}

}
