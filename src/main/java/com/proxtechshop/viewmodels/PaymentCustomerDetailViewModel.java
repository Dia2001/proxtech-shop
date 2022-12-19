package com.proxtechshop.viewmodels;

public class PaymentCustomerDetailViewModel {
	
	private String fullname;
	private String phone;
	private String address;
	
	public PaymentCustomerDetailViewModel() {
		super();
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	
	@Override
	public String toString() {
		return "PaymentCustomerDetailViewModel [fullname=" + fullname + ", phone=" + phone + ", address=" + address
				+ "]";
	}
	

}
