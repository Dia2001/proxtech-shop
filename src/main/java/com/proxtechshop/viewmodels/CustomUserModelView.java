package com.proxtechshop.viewmodels;

public class CustomUserModelView {
	private String fullName;
	private String username;
	private String phone;
	private String address;
	private String createdDate;
	private String newPassword;
	private String password;
	private String image;
	
	public CustomUserModelView(String fullName, String username, String phone, String address, String createdDate,
			String newPassword, String password,String image) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.phone = phone;
		this.address = address;
		this.createdDate = createdDate;
		this.newPassword = newPassword;
		this.password = password;
		this.image=image;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CustomUserModelView() {
		this.fullName = "";
		this.username = "";
		this.phone = "";
		this.address = "";
		this.createdDate = "";
		this.newPassword="";
		this.password="";
		this.image="";
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
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
