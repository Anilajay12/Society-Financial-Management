package com.virtusa.society.model;

public class ChangePassword {
	protected String currentPassword;
	protected String newPassword;
	protected String role;
	protected String name;
	public ChangePassword(String currentPassword, String newPassword, String role,String name) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.role = role;
		this.name=name;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
