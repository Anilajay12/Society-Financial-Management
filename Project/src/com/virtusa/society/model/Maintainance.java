package com.virtusa.society.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Maintainance {
	protected int id;
	protected String name;
	protected String email;
	protected String plotno;
	protected double maintainace;
	protected double extra;
	protected boolean status;
	protected Timestamp paidtime;
	protected double total;
	protected Date start;
	protected Date end;
	
	public Maintainance() {
		
	}
	

	
	public Date getStart() {
		return start;
	}



	public void setStart(Date start) {
		this.start = start;
	}



	public Date getEnd() {
		return end;
	}



	public void setEnd(Date end) {
		this.end = end;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	


	public Maintainance(int id, String name, String email, String plotno, double maintainace, double extra,
			Timestamp paidtime, double total) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.plotno = plotno;
		this.maintainace = maintainace;
		this.extra = extra;
		this.paidtime = paidtime;
		this.total = total;
	}



	public Timestamp getPaidtime() {
		return paidtime;
	}



	public void setPaidtime(Timestamp paidtime) {
		this.paidtime = paidtime;
	}



	public Maintainance(int id, String name, String email, String plotno, double maintainace, double extra,
			double total) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.plotno = plotno;
		this.maintainace = maintainace;
		this.extra = extra;
		this.total = total;
	}



	public Maintainance(int id, String name, String email, String plotno, double maintainace) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.plotno = plotno;
		this.maintainace = maintainace;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public double getExtra() {
		return extra;
	}



	public void setExtra(double extra) {
		this.extra = extra;
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
	public String getPlotno() {
		return plotno;
	}
	public void setPlotno(String plotno) {
		this.plotno = plotno;
	}
	public double getMaintainace() {
		return maintainace;
	}
	public void setMaintainace(double maintainace) {
		this.maintainace = maintainace;
	}
}
