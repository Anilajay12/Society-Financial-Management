package com.virtusa.society.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.virtusa.society.model.Event;


public class EventDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public EventDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insertEvent(Event event) throws SQLException {
		String sql = "INSERT INTO event (eventname, chiefguest, amount,eventdate) VALUES (?, ?, ?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, event.getEventName());
		statement.setString(2, event.getChiefGuest());
		statement.setDouble(3, event.getAmount());
		statement.setDate(4, event.getDateofevent());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public boolean deleteEvent(Event event) throws SQLException {
		String sql = "DELETE FROM event where eventid = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, event.getEventId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateEvent(Event event) throws SQLException {
		String sql = "UPDATE event SET eventid = ?, eventname = ?, eveantlocation = ?,chiefguest=?,amount=?,contact=?,eventdate=?";
		sql += " WHERE eventid = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, event.getEventId());
		statement.setString(2, event.getEventName());
		statement.setString(3, event.getEventLocation());
		statement.setString(4, event.getChiefGuest());
		statement.setDouble(5, event.getAmount());
		statement.setString(6, event.getContactEmail());
		statement.setDate(7, event.getDateofevent());
		statement.setInt(8, event.getEventId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public List<Event> listAllEvents() throws SQLException {
		List<Event> listEvent = new ArrayList<>();

		String sql = "SELECT * FROM event  order by eventdate desc";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("eventid");
			String eventname = resultSet.getString("eventname");
			String location = resultSet.getString("eveantlocation");
			String chiefguest=resultSet.getString("chiefguest");
			double price = resultSet.getFloat("amount");
			String contact=resultSet.getString("contact");
			Date date=resultSet.getDate("eventdate");
			Event event=new Event(id,eventname,location,chiefguest,price,contact,date);
			listEvent.add(event);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEvent;
	}
	
	
	public Event getEvent(int id) throws SQLException {
        Event event = null;
        String sql = "SELECT * FROM event WHERE eventid = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	
			String eventname = resultSet.getString("eventname");
			String location = resultSet.getString("eveantlocation");
			String chiefguest=resultSet.getString("chiefguest");
			double price = resultSet.getFloat("amount");
			String contact=resultSet.getString("contact");
			Date date=resultSet.getDate("eventdate");
			event=new Event(id,eventname,location,chiefguest,price,contact,date);
        }
         
        resultSet.close();
        statement.close();
         
        return event;
    }
	
}
