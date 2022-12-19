package com.proxtechshop.api.response;

import java.math.BigDecimal;

public class ProductsPaymentResponse {

	private String product_id;
	
	private String name;
	
	private String description;
	
	private String thumbnail;
	
	private BigDecimal price;
	
	private int quantity;

	public ProductsPaymentResponse() {
		super();
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductsPaymentResponse [Product_id=" + product_id + ", name=" + name + ", description=" + description
				+ ", thumbnail=" + thumbnail + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
}
