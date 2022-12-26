package com.proxtechshop.api.request;

public class IRCartRequest {

	private String idProduct;

	private int number;

	public IRCartRequest() {
		super();
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "IRCartRequest [idProduct=" + idProduct + ", number=" + number + "]";
	}

	

}
