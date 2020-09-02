package com.virtusa.society.model;

import java.sql.Timestamp;

public class Feedback {
	protected int id;
	protected String name;
	protected String emailId;
	protected String message;
	protected Timestamp requestTime;
	public Feedback(String name, String emailId, String message) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.message = message;
	}
	public Feedback(int id, String name, String emailId, String message, Timestamp requestTime) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.message = message;
		this.requestTime = requestTime;
	}
	public Feedback(int id) {
		super();
		this.id = id;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}

}
