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

import com.virtusa.society.model.Employee;



public class EmployeeDAO {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public EmployeeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null) {
            jdbcConnection.close();
        }
    }
    
    public boolean insertEmployee(com.virtusa.society.model.Employee emp) throws SQLException {
        String sql = "INSERT INTO employee (ename, emailid, dob,address,phone,gender,salary,doj) VALUES (?, ?, ?,?, ?, ?,?, current_date())";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, emp.getEname());
        statement.setString(2, emp.getEmail());
        statement.setDate(3, (java.sql.Date) emp.getDob());
        statement.setString(4, emp.getAddress());
        statement.setString(5, emp.getPhone());
        statement.setString(6,emp.getGender());
        statement.setDouble(7, emp.getSalary());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    
    public boolean deleteEmployee(Employee emp) throws SQLException {
    	String sql = "INSERT INTO employeerecords (ename, emailid, dob,address,phone,gender,salary,doj,dol) VALUES (?, ?, ?,?, ?, ?,?, ?,current_date())";
        connect();
        String query="select *from employee where eid=?";
        PreparedStatement statement1 = jdbcConnection.prepareStatement(query);
        statement1.setInt(1, emp.getEid());
        ResultSet rs=statement1.executeQuery();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        if(rs.next()) {
        statement.setString(1, rs.getString("ename"));
        statement.setString(2, rs.getString("emailid"));
        statement.setDate(3, rs.getDate("dob"));
        statement.setString(4, rs.getString("address"));
        statement.setString(5, rs.getString("phone"));
        statement.setString(6, rs.getString("gender"));
        statement.setDouble(7, rs.getDouble("salary"));
        statement.setDate(8, rs.getDate("doj"));
        
        }
        String sql2 = "DELETE FROM employee where eid = ?";
        connect();
         
        PreparedStatement statement2 = jdbcConnection.prepareStatement(sql2);
        statement2.setInt(1, emp.getEid());
         
        boolean rowDeleted = statement2.executeUpdate() > 0;
        if(rowDeleted) {
        	statement.executeUpdate();
            statement.close();
        }
        statement2.close();
        disconnect();
        return rowDeleted;     
    }
    
    
    public boolean updateEmployee(Employee emp) throws SQLException {
    	
        String sql = "UPDATE employee SET eid=?,ename=?, emailid=?, dob=?,address=?,phone=?,salary=? WHERE eid = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, emp.getEid());
        statement.setString(2, emp.getEname());
        statement.setString(3, emp.getEmail());
        statement.setDate(4, emp.getDob());
        statement.setString(5, emp.getAddress());
        statement.setString(6, emp.getPhone());
        statement.setDouble(7, emp.getSalary());
        statement.setInt(8, emp.getEid());
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public List<Employee> listAllEmployees() throws SQLException {
        List<Employee> listEmployees = new ArrayList<>();
         
        String sql = "SELECT eid,ename, emailid, dob,address,phone,gender,salary,doj from employee";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("eid");
            String name = resultSet.getString("ename");
            String email = resultSet.getString("emailid");
            Date dob=resultSet.getDate("dob");
            String address=resultSet.getString("address");
            String phone=resultSet.getString("phone");
            String gender=resultSet.getString("gender");
            Date doj=resultSet.getDate("doj");
            Double salary = resultSet.getDouble("salary");
             
            Employee emp = new Employee(id,name,email,address,gender,phone,salary,doj,dob);
            listEmployees.add(emp);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listEmployees;
    }
    
    
    public Employee getEmployee(int id) throws SQLException {
        Employee emp = null;
        String sql = "SELECT eid,ename, emailid, dob,address,phone,gender,salary,doj from employee WHERE eid = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("ename");
            String email = resultSet.getString("emailid");
            Date dob=resultSet.getDate("dob");
            String address=resultSet.getString("address");
            String phone=resultSet.getString("phone");
            String gender=resultSet.getString("gender");
            Date doj=resultSet.getDate("doj");
            Double salary = resultSet.getDouble("salary");
             
            emp = new Employee(id,name,email,address,gender,phone,salary,doj,dob);
        }
         
        resultSet.close();
        statement.close();
         
        return emp;
    }

	public Employee profile(Employee profile) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT eid,ename, emailid, dob,address,phone,gender,salary,doj from employee";
		Employee emp=null;
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("eid");
            String name = resultSet.getString("ename");
            String email = resultSet.getString("emailid");
            Date dob=resultSet.getDate("dob");
            String address=resultSet.getString("address");
            String phone=resultSet.getString("phone");
            String gender=resultSet.getString("gender");
            Date doj=resultSet.getDate("doj");
            Double salary = resultSet.getDouble("salary");
             
             emp = new Employee(id,name,email,address,gender,phone,salary,doj,dob);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return emp;
	}
	
	public List<Employee> listPastEmployees() throws SQLException {
        List<Employee> listpastEmployees = new ArrayList<>();
         
        String sql = "SELECT *from employeerecords order by(id)";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("ename");
            String email = resultSet.getString("emailid");
            Date dob=resultSet.getDate("dob");
            String address=resultSet.getString("address");
            String phone=resultSet.getString("phone");
            String gender=resultSet.getString("gender");
            Date doj=resultSet.getDate("doj");
            Date dol=resultSet.getDate("dol");
             
            Employee emp = new Employee(id,name,email,address,gender,phone,doj,dol,dob);
            listpastEmployees.add(emp);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listpastEmployees;
    }
	
}
