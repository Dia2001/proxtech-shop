package com.proxtechshop.viewmodels;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class UserView {
//	
//	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
//	@Column(name = "id", length = 36, nullable = false)
//	private String id;
	private String username;
	private String fullname;
	private String password;
	private Date createdDate;
	private boolean active;
	
	public UserView(String username, String fullname, String password, Date createdDate, boolean active) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.createdDate = createdDate;
		this.active = active;
	}
	public UserView() {
		
	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
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
