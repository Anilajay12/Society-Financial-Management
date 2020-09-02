package com.virtusa.society.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.virtusa.society.model.Maintainance;

public class MaintainanceDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public MaintainanceDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
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

	@SuppressWarnings("deprecation")
	public void insertMaintainance() throws SQLException {
		connect();
		if(java.util.Calendar.getInstance().getTime().getDate()==5) {
		String query1 = "select plotnumber,cust_id,maintainance from plot where status=true";
		PreparedStatement statement1 = jdbcConnection.prepareStatement(query1);
		ResultSet rs1 = statement1.executeQuery();
		while (rs1.next()) {
			String query2 = "select name,email from user where id=" + rs1.getInt("cust_id");
			PreparedStatement statement2 = jdbcConnection.prepareStatement(query2);
			ResultSet rs2 = statement2.executeQuery();
			while (rs2.next()) {
				String sql = "insert into maintainance (name,email,plotno,maintainance) values (?,?,?,?)";
				PreparedStatement statement = jdbcConnection.prepareStatement(sql);
				statement.setString(1,rs2.getString("name"));
				statement.setString(2,rs2.getString("email"));
				statement.setString(3,rs1.getString("plotnumber"));
				statement.setDouble(4, rs1.getDouble("maintainance"));
				statement.executeUpdate();
			}
			statement2.close();
		}
		statement1.close();
		}
		disconnect();
	}
	
	public List<Maintainance> listAllMaintainace() throws SQLException {
        List<Maintainance> list = new ArrayList<>();
        String sql = "SELECT *from maintainance";         
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql); 
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String plotno=resultSet.getString("plotno");
            Double maintainance = resultSet.getDouble("maintainance");
            Maintainance maintain = new Maintainance(id,name,email,plotno,maintainance);
            list.add(maintain);
        }
        resultSet.close();
        statement.close();
         
        disconnect();
        return list;
    }

	
	public List<Maintainance> userPaidMaintainance(Maintainance bill) throws SQLException {
        List<Maintainance> bills = new ArrayList<>();
        String emailid=bill.getEmail();
        String sql = "SELECT id,name,email,plotno,maintainance,fine,total,paidtime from receipts where email='"+emailid+"'";         
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql); 
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String plotno=resultSet.getString("plotno");
            Double maintainance = resultSet.getDouble("maintainance");
            Double extra=resultSet.getDouble("fine");
            Double total=resultSet.getDouble("total");
            Timestamp time=resultSet.getTimestamp("paidtime");
            Maintainance maintain=new Maintainance(id,name,email,plotno,maintainance,extra,time,total);
            bills.add(maintain);
        }
        resultSet.close();
        statement.close();
         
        disconnect();
        return bills;
    }
	
	public List<Maintainance> allPaidBills() throws SQLException {
        List<Maintainance> list = new ArrayList<>();
        String sql = "SELECT *from receipts";         
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql); 
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String plotno=resultSet.getString("plotno");
            Double maintainance = resultSet.getDouble("maintainance");
            Double extra=resultSet.getDouble("fine");
            Double total=resultSet.getDouble("total");
            Timestamp time=resultSet.getTimestamp("paidtime");
            Maintainance maintain=new Maintainance(id,name,email,plotno,maintainance,extra,time,total);
            list.add(maintain);
        }
        resultSet.close();
        statement.close();
         
        disconnect();
        return list;
    }
	
	public List<Maintainance> userUnPaidMaintainance(Maintainance bill) throws SQLException {
        List<Maintainance> list = new ArrayList<>();
        String emailid=bill.getEmail();
        String sql = "SELECT id,name,email,plotno,maintainance from maintainance where email='"+emailid+"'";         
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql); 
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String plotno=resultSet.getString("plotno");
            Double maintainance = resultSet.getDouble("maintainance");
            Maintainance maintain = new Maintainance(id,name,email,plotno,maintainance);
            list.add(maintain);
        }
        resultSet.close();
        statement.close();
         
        disconnect();
        return list;
    }

	@SuppressWarnings("deprecation")
	public Maintainance getExistBill(int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT id,name,email,plotno,maintainance from maintainance where id="+id;
		Maintainance maintain=null;
		connect();
		Calendar calendar=Calendar.getInstance();         
        PreparedStatement statement=jdbcConnection.prepareStatement(sql); 
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String plotno=resultSet.getString("plotno");
            Double maintainance = resultSet.getDouble("maintainance");
            Double extra=0.0;
            if(calendar.getTime().getDate()>10) {
            	extra=(double) 100;
            }
            Double total=maintainance+extra;
            maintain = new Maintainance(id,name,email,plotno,maintainance,extra,total);
        }
        resultSet.close();
        statement.close();
         
        disconnect();
        return maintain;
	}

	public boolean payment(Maintainance payment) throws SQLException {
		String sql="delete from maintainance where id="+payment.getId();
		connect();
		boolean success=false;
		PreparedStatement statement=jdbcConnection.prepareStatement(sql);
		boolean resultSet=statement.executeUpdate()>0;
		if(resultSet) {
			sql="insert into receipts(name,email,plotno,maintainance,fine,total) values (?,?,?,?,?,?)";
			statement=jdbcConnection.prepareStatement(sql);
			statement.setString(1, payment.getName());
			statement.setString(2, payment.getEmail());
			statement.setString(3, payment.getPlotno());
			statement.setDouble(4, payment.getMaintainace());
			statement.setDouble(5, payment.getExtra());
			statement.setDouble(6, payment.getTotal());
			success=statement.executeUpdate()>0;
			
		}
		statement.close();
		disconnect();
		return success;
	}

	public List<Object> report(Maintainance maintainance) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select sum(maintainance) from receipts where paidtime>='"+maintainance.getStart()+"' and paidtime<='"+maintainance.getEnd()+"'";
		List<Object> list=new ArrayList<>();
		connect();
		PreparedStatement statement=jdbcConnection.prepareStatement(sql);
		ResultSet resultSet=statement.executeQuery();
		if(resultSet.next()) {
			list.add(resultSet.getDouble("sum(maintainance)"));
		}
		sql="select sum(amount) from event where eventdate>='"+maintainance.getStart()+"' and eventdate<='"+maintainance.getEnd()+"'";
		statement=jdbcConnection.prepareStatement(sql);
		resultSet=statement.executeQuery();
		if(resultSet.next()) {
			list.add(resultSet.getDouble("sum(amount)"));
		}
		list.add(maintainance.getStart());
		list.add(maintainance.getEnd());
		resultSet.close();
		statement.close();
		disconnect();
		return list;
	}
	
	
}
