package com.proxtechshop.utils;

import java.io.Serializable;

public class OrderDetailId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;
	
	private String productId;

	public OrderDetailId(String orderId, String productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
