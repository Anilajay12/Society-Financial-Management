package com.virtusa.society.model;

import java.sql.Date;

public class Employee {
	protected int eid;
	protected String ename;
	protected String email;
	protected String address;
	protected String gender;
	protected String phone;
	protected double salary;
	protected Date doj;
	protected Date dol;
	protected Date dob;
	protected String role;
	
	public Employee(int eid, String ename, String email, String address, String gender, String phone,
			Date doj, Date dol, Date dob) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.doj = doj;
		this.dol = dol;
		this.dob = dob;
	}
	public Employee(String email, String role) {
		super();
		this.email = email;
		this.role = role;
	}
	public Employee(int eid, String ename, String email, String address, String gender, String phone, double salary,
			Date doj, Date dob) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.salary = salary;
		this.doj = doj;
		this.dob = dob;
	}
	public Employee() {}
	public Employee(String ename, String email, String address, String gender, String phone, double salary, 
			Date dob) {
		super();
		this.ename = ename;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.salary = salary;
		
		this.dob = dob;
	}
	public Employee(int eid, String ename, String email, String address, String phone, double salary,Date dob) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
		this.dob = dob;
	}
	
	
	public Employee(int eid, String ename, String email, String address, String gender, String phone, double salary,
			Date dob) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.salary = salary;
		this.dob = dob;
	}
	public Employee(int eid) {
		super();
		this.eid = eid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public java.sql.Date getDoj() {
		return (java.sql.Date) doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}
