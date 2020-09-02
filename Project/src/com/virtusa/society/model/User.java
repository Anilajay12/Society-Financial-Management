package com.virtusa.society.model;

import java.sql.Timestamp;

public class User {
	protected int id;
	protected String name;
	protected String email;
	protected String password;
	protected String country;
	protected String state;
	protected String district;
	protected Timestamp checkin;
	protected Timestamp checkout;
	protected String plotType;
	protected String phone;
	protected String plotno;
	protected double rent;
	protected int familymembercount;
	protected String role;
	
	public String getRole() {
		return role;
	}

	



	public User(int id, String name, String email, String country, String state, String district, Timestamp checkin,
			Timestamp checkout, String phone, String plotno, int familymembercount) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
		this.state = state;
		this.district = district;
		this.checkin = checkin;
		this.checkout = checkout;
		this.phone = phone;
		this.plotno = plotno;
		this.familymembercount = familymembercount;
	}






	public User(int id, String name, String email, int familymembercount,String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.familymembercount = familymembercount;
		this.phone=phone;
	}






	public void setRole(String role) {
		this.role = role;
	}





	public User() {}
	
	
	
	
	
	public User(String email,String role) {
		super();
		this.email = email;
		this.role=role;
	}





	public User(String name, String email, String country, String state, String district, Timestamp checkin,
			String phone, String plotno, int familymembercount) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.state = state;
		this.district = district;
		this.checkin = checkin;
		this.phone = phone;
		this.plotno = plotno;
		this.familymembercount = familymembercount;
	}





	public Timestamp getCheckout() {
		return checkout;
	}






	public void setCheckout(Timestamp checkout) {
		this.checkout = checkout;
	}






	public User(int id) {
		super();
		this.id = id;
	}

	
	public User(String pid) {
		super();
		this.plotno = pid;
	}



	public User(int id,String name, String email, String plotType, String phone, String plotno, double rent,
			int familymembercount) {
		super();
		this.id=id;
		this.name = name;
		this.email = email;
		this.plotType = plotType;
		this.phone = phone;
		this.plotno = plotno;
		this.rent = rent;
		this.familymembercount = familymembercount;
	}





	public User(int id, String name, String email, String country, String state, String district, Timestamp checkin,
			String plotType, String phone, String plotno, double rent, int familymembercount) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
		this.state = state;
		this.district = district;
		this.checkin = checkin;
		this.plotType = plotType;
		this.phone = phone;
		this.plotno = plotno;
		this.rent = rent;
		this.familymembercount = familymembercount;
	}





	public User(int id, String name, String email, Timestamp checkin, String plotType, String phone, String plotno) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.checkin = checkin;
		this.plotType = plotType;
		this.phone = phone;
		this.plotno = plotno;
	}





	public User(int id, String name, String email, String plotType, String plotno, double rent, int familymembercount) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.plotType = plotType;
		this.plotno = plotno;
		this.rent = rent;
		this.familymembercount = familymembercount;
	}
	public User(String name, String email, String country, String state, String district, String plotType, String phone,
			String plotno, double rent, int familymembercount) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.state = state;
		this.district = district;
		this.plotType = plotType;
		this.phone = phone;
		this.plotno = plotno;
		this.rent = rent;
		this.familymembercount = familymembercount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Timestamp getCheckin() {
		return checkin;
	}
	public void setCheckin(Timestamp checkin) {
		this.checkin = checkin;
	}
	public String getPlotType() {
		return plotType;
	}
	public void setPlotType(String plotType) {
		this.plotType = plotType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlotno() {
		return plotno;
	}
	public void setPlotno(String plotno) {
		this.plotno = plotno;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public int getFamilymembercount() {
		return familymembercount;
	}
	public void setFamilymembercount(int familymembercount) {
		this.familymembercount = familymembercount;
	}
	
	
}
