package com.virtusa.society.model;

import java.sql.Date;

public class Event {
	protected int eventId;
	protected String eventName;
	protected String eventLocation;
	protected String chiefGuest;
	protected double amount;
	protected String contactEmail;
	protected Date dateofevent;

	public Event() {
	}
	public Event(int eventId, String eventName, String eventLocation, String chiefGuest, double amount,
			String contactEmail,Date dateofevent) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventLocation = eventLocation;
		this.chiefGuest = chiefGuest;
		this.amount = amount;
		this.contactEmail = contactEmail;
		this.dateofevent=dateofevent;
	}
	public Date getDateofevent() {
		return dateofevent;
	}
	public void setDateofevent(Date dateofevent) {
		this.dateofevent = dateofevent;
	}
	public Event(int eventId) {
		super();
		this.eventId = eventId;
	}
	public Event(String eventName, String chiefGuest, double amount,Date dateofevent) {
		super();
		this.eventName = eventName;
		this.chiefGuest = chiefGuest;
		this.amount = amount;
		this.dateofevent=dateofevent;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public String getChiefGuest() {
		return chiefGuest;
	}
	public void setChiefGuest(String chiefGuest) {
		this.chiefGuest = chiefGuest;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	
}
