package com.proxtechshop.api.response;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {
	
	private String id;
	
	private String orderStatusName;
	
	private BigDecimal checkoutPrice;
	
	private List<ProductOrderResponse>  products;

	public OrderResponse() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public BigDecimal getCheckoutPrice() {
		return checkoutPrice;
	}

	public void setCheckoutPrice(BigDecimal checkoutPrice) {
		this.checkoutPrice = checkoutPrice;
	}

	public List<ProductOrderResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductOrderResponse> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OrderResponse [id=" + id + ", orderStatusName=" + orderStatusName + ", checkoutPrice=" + checkoutPrice
				+ ", products=" + products + "]";
	}
	
	

}
