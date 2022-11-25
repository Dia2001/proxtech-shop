package com.proxtechshop.viewmodels;

import java.util.Date;

public class UserView {

	private String username;
	private String fullname;
	private String password;
	private Date createdDate;
	private boolean active;
	private String msg;

	public UserView(String username, String fullname, String password, Date createdDate, boolean active) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.createdDate = createdDate;
		this.active = active;
		msg = "";
	}

	public UserView() {
		msg = "";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
