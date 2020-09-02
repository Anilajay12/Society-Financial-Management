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

import com.virtusa.society.model.User;


public class UserDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public boolean insertUser(User user) throws SQLException {
		String sql = "INSERT INTO user (name, email, country,state,district,plottype,phone,rent,plotno,familycount) VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?)";
		connect();
		String query1="select plottype from plotdetails where plotid="+user.getPlotType();
		PreparedStatement statement1 = jdbcConnection.prepareStatement(query1);
		ResultSet rs1=statement1.executeQuery();
		String plottype=null;
		if(rs1.next()) {
			plottype=rs1.getString("plottype");
		}
		query1="select plotnumber from plot where pid="+user.getPlotno();
		statement1 = jdbcConnection.prepareStatement(query1);
		rs1=statement1.executeQuery();
		String plotno=null;
		if(rs1.next()) {
			plotno=rs1.getString("plotnumber");
		}
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getEmail());
		statement.setString(3, user.getCountry());
		statement.setString(4, user.getState());
		statement.setString(5, user.getDistrict());
		statement.setString(6, plottype);
		statement.setString(7, user.getPhone());
		statement.setDouble(8, user.getRent());
		statement.setString(9, plotno);
		statement.setInt(10, user.getFamilymembercount());
		boolean rowInserted = statement.executeUpdate() > 0;
		if(rowInserted) {
			sql="select id from user where email='"+user.getEmail()+"'";
			statement = jdbcConnection.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			rs.next();
			int userid=rs.getInt("id");
			sql="update plot set status=1,cust_id=+"+userid+" where pid="+user.getPlotno();
			statement = jdbcConnection.prepareStatement(sql);
			statement.executeUpdate();
		}
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<User> listAllUsers() throws SQLException {
		List<User> listUsers = new ArrayList<>();

		String sql = "SELECT id,name,email,plottype,plotno,checkin,phone from user";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id=resultSet.getInt("id");
			String name=resultSet.getString("name");
			String email=resultSet.getString("email");
			String plottype=resultSet.getString("plottype");
			String plotno=resultSet.getString("plotno");
			Timestamp checkin=resultSet.getTimestamp("checkin");
			String phone=resultSet.getString("phone");
			User user=new User(id,name,email,checkin,plottype,phone,plotno);
			listUsers.add(user);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listUsers;
	}

	public boolean deleteUser(User user) throws SQLException {
		User user1=null;
		int id=user.getId();
		String query="select *from user where id="+id;
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(query);
		ResultSet rs=statement.executeQuery();
		if(rs.next()) {
			String name=rs.getString("name");
			String email=rs.getString("email");
			String plotno=rs.getString("plotno");
			int f_count=rs.getInt("familycount");
			Timestamp checkin=rs.getTimestamp("checkin");
			String phone=rs.getString("phone");
			String country=rs.getString("country");
			String state=rs.getString("state");
			String district=rs.getString("district");
			user1=new User(name,email,country,state,district,checkin,phone,plotno,f_count);
		}
		query = "INSERT INTO userRecords (name, email, country,state,district,phone,plotno,familycount,checkin,checkout) VALUES (?, ?, ?,?, ?, ?,?, ?, ?,current_timestamp)";
	    statement = jdbcConnection.prepareStatement(query);
		statement.setString(1, user1.getName());
		statement.setString(2, user1.getEmail());
		statement.setString(3, user1.getCountry());
		statement.setString(4, user1.getState());
		statement.setString(5, user1.getDistrict());
		statement.setString(6, user1.getPhone());
		statement.setString(7, user1.getPlotno());
		statement.setInt(8, user1.getFamilymembercount());
		statement.setTimestamp(9, user1.getCheckin());
		
		String sql = "DELETE FROM user where id = ?";
		PreparedStatement statement1 = jdbcConnection.prepareStatement(sql);
		statement1.setInt(1, user.getId());
		boolean rowDeleted = statement1.executeUpdate() > 0;
		if(rowDeleted) {
			sql="select pid from plot where cust_id="+id;
			statement1 = jdbcConnection.prepareStatement(sql);
			ResultSet rs1=statement1.executeQuery();
			rs1.next();
			int plotid=rs1.getInt("pid");
			sql="update plot set status=false where pid="+plotid;
			statement1 = jdbcConnection.prepareStatement(sql);
			statement1.executeUpdate();
			sql="update plot set cust_id=-1 where pid="+plotid;
			statement1 = jdbcConnection.prepareStatement(sql);
			statement1.executeUpdate();
			
			statement.executeUpdate();
			statement1.close();
			rs1.close();
			
			
		}
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public User getuserDetails(int id) throws SQLException {
		User user = null;
		String sql = "SELECT name,email,plottype,plotno,rent,phone,familycount from user WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String name=resultSet.getString("name");
			String email=resultSet.getString("email");
			String plottype=resultSet.getString("plottype");
			String plotno=resultSet.getString("plotno");
			Double rent =resultSet.getDouble("rent");
			int f_count=resultSet.getInt("familycount");
			String phone=resultSet.getString("phone");
			user=new User(id,name,email,plottype,phone,plotno,rent,f_count);
		}

		resultSet.close();
		statement.close();

		return user;
	}
	
	public boolean updateUser(User user) throws SQLException {
    	
        String sql = "UPDATE user SET name=? ,email=?,plottype=?,plotno=?,rent=?,phone=?,familycount=? WHERE id = ?";
        connect();
        String query="select pid from plot where cust_id="+user.getId();
        int plotno=0;
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        PreparedStatement statement2 = jdbcConnection.prepareStatement(query);
		ResultSet rs=statement2.executeQuery();
		if(rs.next()) {
			plotno=rs.getInt("pid");
		}
        statement.setString(1,user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPlotType());
        statement.setString(4, user.getPlotno());
        statement.setDouble(5, user.getRent());
        statement.setString(6, user.getPhone());
        statement.setInt(7, user.getFamilymembercount());
        statement.setInt(8, user.getId());
        boolean rowUpdated = statement.executeUpdate() > 0;
        if(rowUpdated) {
        	sql="update plot set status=1 where pid="+user.getPlotno();
        	PreparedStatement statement1 = jdbcConnection.prepareStatement(sql);
			statement1.executeUpdate();
			sql="update plot set status=0 where pid="+plotno;
			statement1 = jdbcConnection.prepareStatement(sql);
			statement1.executeUpdate();
			boolean update=statement1.executeUpdate()>0;
			if(update) {
				sql="update plot set cust_id=-1 where pid="+plotno;
				statement1 = jdbcConnection.prepareStatement(sql);
				statement1.executeUpdate();
				sql="update plot set cust_id=? where pid="+user.getPlotno();
				statement1 = jdbcConnection.prepareStatement(sql);
				statement1.setInt(1,user.getId());
				statement1.executeUpdate();
			}
			statement1.close();
        }
        statement.close();
        disconnect();
        return rowUpdated;     
    }
	
	
	
	public User profile(User profile) throws SQLException {
		String sql = "SELECT id,name,email,country,state,district,checkin,plottype,plotno,rent,phone,familycount from "+profile.getRole()+" WHERE email = '"+profile.getEmail()+"'";
		User user=null;
		connect();
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id=resultSet.getInt("id");
			String name=resultSet.getString("name");
			String email=resultSet.getString("email");
			String country=resultSet.getString("country");
			String state=resultSet.getString("state");
			String district=resultSet.getString("district");
			String plotno=resultSet.getString("plotno");
			String plottype=resultSet.getString("plottype");
			Double rent=resultSet.getDouble("rent");
			Timestamp checkin=resultSet.getTimestamp("checkin");
			String phone=resultSet.getString("phone");
			int count=resultSet.getInt("familycount");
			user=new User(id,name,email,country,state,district,checkin,plottype,phone,plotno,rent,count);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return user;
	}

	public boolean updateUserProfile(User user) throws SQLException {
    	
        String sql = "UPDATE user SET name=? ,email=?,phone=?,familycount=? WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPhone());
        statement.setInt(4, user.getFamilymembercount());
        statement.setInt(5, user.getId());
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
	
	public List<User> listPastUsers() throws SQLException {
		List<User> listUsers = new ArrayList<>();

		String sql = "SELECT *from userrecords";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id=resultSet.getInt("id");
			String name=resultSet.getString("name");
			String email=resultSet.getString("email");
			String plotno=resultSet.getString("plotno");
			
			
			String country=resultSet.getString("country");
			String state=resultSet.getString("state");
			String district=resultSet.getString("district");
			int fc=resultSet.getInt("familycount");
			Timestamp checkin=resultSet.getTimestamp("checkin");
			String phone=resultSet.getString("phone");
			Timestamp checkout=resultSet.getTimestamp("checkout");
			User user=new User(id,name,email,country,state,district,checkin,checkout,phone,plotno,fc);
			listUsers.add(user);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listUsers;
		
		

	}
	
	
	public List<String> listUserMails() throws SQLException{
		List<String> listUsers=new ArrayList<>();
		connect();
		String query="select email from user";
		PreparedStatement statement=jdbcConnection.prepareStatement(query);
		ResultSet resultSet=statement.executeQuery();
		while(resultSet.next()) {
			String emailid=resultSet.getString("email");
			listUsers.add(emailid);
		}
		
		disconnect();
		return listUsers;
	}
	
}
