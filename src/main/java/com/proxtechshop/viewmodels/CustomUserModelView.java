package com.proxtechshop.viewmodels;

import java.util.Date;

import com.proxtechshop.entities.User;
import com.proxtechshop.entities.Customer;

public class CustomUserModelView {
	private String userId;

	private String id;
	private String fullName;
	private String username;
	private String phone;
	private String address;
	private Date createdDate;
	private String newPassword;
	private String password;
	private boolean isCustomer = false;
	private String image;

	public CustomUserModelView(String fullName, String username, String phone, String address, Date createdDate,
			String newPassword, String password, String image) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.phone = phone;
		this.address = address;
		this.createdDate = createdDate;
		this.newPassword = newPassword;
		this.password = password;
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CustomUserModelView(User user, Customer customer, boolean isCustomer) {
		this.userId=user.getId();
		this.id=customer.getId();
		this.fullName = customer.getFullName();
		this.username = user.getUsername();
		this.phone = customer.getPhone();
		this.address = customer.getAddress();
		this.createdDate = customer.getCreatedDate();
		this.image=customer.getImage();
		this.newPassword = "";
		this.password = user.getPassword();
		this.isCustomer = isCustomer;
	}

	public CustomUserModelView() {
		this.fullName = "";
		this.username = "";
		this.phone = "";
		this.address = "";
		this.createdDate = null;
		this.newPassword = "";
		this.password = "";
		this.image = "";
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
}
