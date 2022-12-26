package com.proxtechshop.viewmodels;

import com.proxtechshop.entities.Product;

public class ProductOrder {

	private Product product;

	private int number;

	private String value;

	public ProductOrder() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ProductOrder [product=" + product + ", number=" + number + ", value=" + value + "]";
	}

}
