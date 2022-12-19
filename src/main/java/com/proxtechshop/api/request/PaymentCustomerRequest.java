package com.proxtechshop.api.request;

import java.util.List;

public class PaymentCustomerRequest {
	
	private List<String> listProductId;
	
	private String note;
	
	private String addressShip;

	public PaymentCustomerRequest() {
		super();
	}

	public List<String> getListProductId() {
		return listProductId;
	}

	public void setListProductId(List<String> listProductId) {
		this.listProductId = listProductId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAddressShip() {
		return addressShip;
	}

	public void setAddressShip(String addressShip) {
		this.addressShip = addressShip;
	}

	@Override
	public String toString() {
		return "OrderDetailCustomer [listProductId=" + listProductId + ", note=" + note + ", addressShip=" + addressShip
				+ "]";
	}
	
	
}
